package com.andriawan.askme.ui.screens.login.viewmodel

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andriawan.askme.R
import com.andriawan.askme.domain.usecases.auth.SignInUseCase
import com.andriawan.askme.ui.screens.login.models.LoginUiEvent
import com.andriawan.askme.ui.screens.login.models.LoginUiState
import com.andriawan.askme.utils.Constants
import com.andriawan.askme.utils.Constants.EMPTY
import com.andriawan.askme.utils.Constants.MINUS_ONE
import com.andriawan.askme.utils.ResultState
import com.andriawan.askme.utils.SharedPreferencesHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    sharedPreferencesHelper: SharedPreferencesHelper
) : ViewModel() {

    var loginUiState by mutableStateOf(LoginUiState())
        private set

    init {
        if (sharedPreferencesHelper.getString(Constants.ACCESS_TOKEN_KEY).isNotEmpty()) {

        }
    }

    fun onEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.OnEmailChanged -> {
                validateEmail(event.email)
                validatePassword(loginUiState.password)
                loginUiState = loginUiState.copy(
                    signInButtonEnabled = loginUiState.emailError == MINUS_ONE
                            && loginUiState.passwordError == MINUS_ONE
                )
            }
            is LoginUiEvent.OnPasswordChanged -> {
                validateEmail(loginUiState.email)
                validatePassword(event.password)
                loginUiState = loginUiState.copy(
                    signInButtonEnabled = loginUiState.emailError == MINUS_ONE
                            && loginUiState.passwordError == MINUS_ONE
                )
            }
            is LoginUiEvent.OnPasswordVisibilityChanged -> loginUiState =
                loginUiState.copy(isPasswordVisible = event.isPasswordVisible)
            LoginUiEvent.OnSubmitClicked -> {
                signIn(loginUiState.email, loginUiState.password)
                loginUiState = loginUiState.copy(
                    signInButtonEnabled = false
                )
            }
        }
    }

    private fun signIn(email: String, password: String) {
        val param = SignInUseCase.Param(email, password)

        viewModelScope.launch {
            signInUseCase.execute(param).collectLatest {
                when (it) {
                    is ResultState.Loading -> {
                        loginUiState = loginUiState.copy(
                            showLoading = true,
                            signInButtonEnabled = false
                        )
                    }

                    is ResultState.Success -> {

                    }

                    is ResultState.Error -> {
                        loginUiState = loginUiState.copy(
                            showLoading = false,
                            signInButtonEnabled = true,
                            showError = it.exception
                        )
                    }
                }
            }
        }
    }

    private fun validateEmail(email: String) {
        loginUiState = if (email.isBlank()) {
            loginUiState.copy(
                email = email,
                emailError = R.string.error_empty_email_error_text
            )
        } else if (!Pattern.matches(Patterns.EMAIL_ADDRESS.pattern(), email)) {
            loginUiState.copy(
                email = email,
                emailError = R.string.error_valid_email_error_text
            )
        } else {
            loginUiState.copy(
                email = email,
                emailError = MINUS_ONE
            )
        }
    }

    private fun validatePassword(password: String) {
        loginUiState = if (password.isBlank()) {
            loginUiState.copy(
                password = password,
                passwordError = R.string.error_empty_password_error_text
            )
        } else if (password.length !in 8..14) {
            loginUiState.copy(
                password = password,
                passwordError = R.string.error_length_password_error_text
            )
        } else {
            loginUiState.copy(
                password = password,
                passwordError = MINUS_ONE
            )
        }
    }
}