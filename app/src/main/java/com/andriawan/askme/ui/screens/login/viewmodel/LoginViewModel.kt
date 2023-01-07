package com.andriawan.askme.ui.screens.login.viewmodel

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andriawan.askme.R
import com.andriawan.askme.domain.usecases.auth.SignInUseCase
import com.andriawan.askme.ui.screens.login.models.LoginUiEvent
import com.andriawan.askme.ui.screens.login.models.LoginUiState
import com.andriawan.askme.utils.Constants.MINUS_ONE
import com.andriawan.askme.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    var uiState by mutableStateOf(LoginUiState())
        private set

    private val _loginStatusChanged = MutableSharedFlow<Boolean>()
    val loginStatusChanged = _loginStatusChanged.asSharedFlow()

    private val _showErrorMessage = MutableSharedFlow<Exception>()
    val showErrorMessage = _showErrorMessage.asSharedFlow()

    fun onEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.OnEmailChanged -> {
                validateEmail(event.email)
                validatePassword(uiState.password)
                uiState = uiState.copy(
                    signInButtonEnabled = uiState.emailError == MINUS_ONE
                            && uiState.passwordError == MINUS_ONE
                )
            }
            is LoginUiEvent.OnPasswordChanged -> {
                validateEmail(uiState.email)
                validatePassword(event.password)
                uiState = uiState.copy(
                    signInButtonEnabled = uiState.emailError == MINUS_ONE
                            && uiState.passwordError == MINUS_ONE
                )
            }
            is LoginUiEvent.OnPasswordVisibilityChanged -> uiState =
                uiState.copy(isPasswordVisible = event.isPasswordVisible)
            LoginUiEvent.OnSubmitClicked -> {
                signIn(uiState.email, uiState.password)
                uiState = uiState.copy(
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
                        uiState = uiState.copy(
                            showLoading = true,
                            signInButtonEnabled = false
                        )
                    }

                    is ResultState.Success -> {
                        _loginStatusChanged.emit(true)
                    }

                    is ResultState.Error -> {
                        _showErrorMessage.emit(it.exception)
                        uiState = uiState.copy(
                            showLoading = false,
                            signInButtonEnabled = true
                        )
                    }
                }
            }
        }
    }

    private fun validateEmail(email: String) {
        uiState = if (email.isBlank()) {
            uiState.copy(
                email = email,
                emailError = R.string.error_empty_email_error_text
            )
        } else if (!Pattern.matches(Patterns.EMAIL_ADDRESS.pattern(), email)) {
            uiState.copy(
                email = email,
                emailError = R.string.error_valid_email_error_text
            )
        } else {
            uiState.copy(
                email = email,
                emailError = MINUS_ONE
            )
        }
    }

    private fun validatePassword(password: String) {
        uiState = if (password.isBlank()) {
            uiState.copy(
                password = password,
                passwordError = R.string.error_empty_password_error_text
            )
        } else if (password.length !in 8..14) {
            uiState.copy(
                password = password,
                passwordError = R.string.error_length_password_error_text
            )
        } else {
            uiState.copy(
                password = password,
                passwordError = MINUS_ONE
            )
        }
    }
}