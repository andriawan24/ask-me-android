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

    private val _signInResult = MutableLiveData<ResultState<String?>>()
    val signInResult: LiveData<ResultState<String?>> = _signInResult

    var loginUiState by mutableStateOf(LoginUiState())
        private set

    init {
        if (sharedPreferencesHelper.getString(Constants.ACCESS_TOKEN_KEY).isNotEmpty()) {
            _signInResult.value = ResultState.Success(EMPTY)
        }
    }

    fun onEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.OnEmailChanged -> validateEmail(event.email)
            is LoginUiEvent.OnPasswordChanged -> validatePassword(event.password)
            is LoginUiEvent.OnPasswordVisibilityChanged -> loginUiState =
                loginUiState.copy(isPasswordVisible = event.isPasswordVisible)
            LoginUiEvent.OnSubmitClicked -> signIn(loginUiState.email, loginUiState.password)
        }
    }

    private fun signIn(email: String, password: String) {
        val param = SignInUseCase.Param(email, password)

        viewModelScope.launch {
            signInUseCase.execute(param).collectLatest { _signInResult.value = it }
        }
    }

    private fun validateEmail(email: String) {
        loginUiState = if (email.isBlank()) {
            loginUiState.copy(
                email = email,
                emailError = R.string.empty_email_error_text
            )
        } else if (!Pattern.matches(Patterns.EMAIL_ADDRESS.pattern(), email)) {
            loginUiState.copy(
                email = email,
                emailError = R.string.valid_email_error_text
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
                passwordError = R.string.empty_password_error_text
            )
        } else {
            loginUiState.copy(
                password = password,
                passwordError = MINUS_ONE
            )
        }
    }
}