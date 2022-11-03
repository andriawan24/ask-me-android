package com.andriawan.askme.ui.screens.login.models

sealed class LoginUiEvent {
    class OnEmailChanged(val email: String): LoginUiEvent()
    class OnPasswordChanged(val password: String): LoginUiEvent()
    class OnPasswordVisibilityChanged(val isPasswordVisible: Boolean): LoginUiEvent()
    object OnSubmitClicked : LoginUiEvent()
}