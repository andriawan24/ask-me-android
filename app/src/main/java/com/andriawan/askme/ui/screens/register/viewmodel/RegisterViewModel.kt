package com.andriawan.askme.ui.screens.register.viewmodel

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andriawan.askme.R
import com.andriawan.askme.domain.models.UserModel
import com.andriawan.askme.domain.usecases.auth.SignUpUseCase
import com.andriawan.askme.ui.screens.register.models.RegisterUiEvent
import com.andriawan.askme.ui.screens.register.models.RegisterUiState
import com.andriawan.askme.utils.Constants
import com.andriawan.askme.utils.None
import com.andriawan.askme.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    var state by mutableStateOf(RegisterUiState())
        private set

    private val _signUpResponse = MutableLiveData<ResultState<UserModel?>>()
    val signUpResponse: LiveData<ResultState<UserModel?>> = _signUpResponse

    private val _showErrorMessage = MutableSharedFlow<Exception>()
    val showErrorMessage = _showErrorMessage.asSharedFlow()

    private val _navigateToLogin = MutableSharedFlow<None>()
    val navigateToLogin = _navigateToLogin.asSharedFlow()

    private fun signUp(name: String, email: String, password: String) {
        val param = SignUpUseCase.Param(
            name = name,
            email = email,
            password = password
        )
        viewModelScope.launch {
            signUpUseCase.execute(param).collectLatest {
                when (it) {
                    is ResultState.Error -> {
                        _showErrorMessage.emit(it.exception)
                    }
                    is ResultState.Loading -> {
                        // Do nothing
                    }
                    is ResultState.Success -> {
                        _navigateToLogin.emit(None)
                    }
                }
            }
        }
    }

    fun onEvent(event: RegisterUiEvent) {
        when (event) {
            RegisterUiEvent.OnSignUp -> {
                signUp(state.name, state.email, state.password)
            }
            is RegisterUiEvent.OnEmailChanged -> {
                state = state.copy(email = event.email)
                validateInput()
            }
            is RegisterUiEvent.OnNameChanged -> {
                state = state.copy(name = event.name)
                validateInput()
            }
            is RegisterUiEvent.OnPasswordChanged -> {
                state = state.copy(password = event.password)
                validateInput()
            }
            is RegisterUiEvent.OnPasswordConfirmationChanged -> {
                state = state.copy(passwordConfirmation = event.passwordConfirmation)
                validateInput()
            }
            is RegisterUiEvent.OnPasswordConfirmationVisibilityChanged -> state =
                state.copy(passwordConfirmationVisible = event.isVisible)
            is RegisterUiEvent.OnPasswordVisibilityChanged -> state =
                state.copy(passwordVisible = event.isVisible)
        }
    }

    private fun validateInput() {
        state = validateName(state.name)
        state = validateEmail(state.email)
        state = validatePassword(state.password)
        state = validatePasswordConfirmation(state.passwordConfirmation)
        validateButtonEnabled()
    }

    private fun validateButtonEnabled() {
        state = state.copy(
            registerButtonEnabled = state.nameError == Constants.MINUS_ONE &&
                    state.emailError == Constants.MINUS_ONE &&
                    state.passwordError == Constants.MINUS_ONE &&
                    state.passwordConfirmationError == Constants.MINUS_ONE
        )
    }

    private fun validateName(name: String): RegisterUiState {
        return if (name.isBlank()) {
            state.copy(nameError = R.string.error_empty_name)
        } else {
            state.copy(nameError = Constants.MINUS_ONE)
        }
    }

    private fun validateEmail(email: String): RegisterUiState {
        return if (email.isBlank()) {
            state.copy(emailError = R.string.error_empty_email_error_text)
        } else if (!Pattern.matches(Patterns.EMAIL_ADDRESS.pattern(), email)) {
            state.copy(emailError = R.string.error_valid_email_error_text)
        } else {
            state.copy(emailError = Constants.MINUS_ONE)
        }
    }

    private fun validatePassword(password: String): RegisterUiState {
        return if (password.isBlank()) {
            state.copy(passwordError = R.string.error_empty_password_error_text)
        } else if (password.length !in Constants.MINIMUM_PASSWORD_LENGTH..Constants.MAXIMUM_PASSWORD_LENGTH) {
            state.copy(passwordError = R.string.error_length_password_error_text)
        } else {
            state.copy(passwordError = Constants.MINUS_ONE)
        }
    }

    private fun validatePasswordConfirmation(password: String): RegisterUiState {
        return if (password.isBlank() && password != state.password) {
            state.copy(passwordConfirmationError = R.string.error_invalid_confirmation_password_error_text)
        } else {
            state.copy(passwordConfirmationError = Constants.MINUS_ONE)
        }
    }
//
//    fun validatePassword(password: Editable?): Int? {
//        return if (password.isNullOrBlank()) {
//            R.string.empty_password
//        } else if (password.length !in Constants.MINIMUM_PASSWORD_LENGTH..Constants.MAXIMUM_PASSWORD_LENGTH) {
//            R.string.length_password
//        } else {
//            null
//        }
//    }
//
//    fun validatePasswordConfirmation(password: Editable?, passwordConfirmation: Editable?): Int? {
//        return if (password.toString() != passwordConfirmation.toString()) {
//            R.string.invalid_password_confirmation
//        } else {
//            null
//        }
//    }
}