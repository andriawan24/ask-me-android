package com.andriawan.askme.ui.screens.register.models

sealed class RegisterUiEvent {
    class OnNameChanged(val name: String): RegisterUiEvent()
    class OnEmailChanged(val email: String): RegisterUiEvent()
    class OnPasswordChanged(val password: String): RegisterUiEvent()
    class OnPasswordConfirmationChanged(val passwordConfirmation: String): RegisterUiEvent()
    object OnSignUp : RegisterUiEvent()
    class OnPasswordVisibilityChanged(val isVisible: Boolean): RegisterUiEvent()
    class OnPasswordConfirmationVisibilityChanged(val isVisible: Boolean): RegisterUiEvent()
}