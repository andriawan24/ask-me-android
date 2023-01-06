package com.andriawan.askme.ui.screens.login.models

import androidx.annotation.StringRes
import com.andriawan.askme.utils.Constants.EMPTY
import com.andriawan.askme.utils.Constants.MINUS_ONE

data class LoginUiState(
    val email: String = EMPTY,
    val password: String = EMPTY,
    @StringRes val emailError: Int = MINUS_ONE,
    @StringRes val passwordError: Int = MINUS_ONE,
    val isPasswordVisible: Boolean = false,
    val signInButtonEnabled: Boolean = false,
    val showError: Exception? = null,
    val showLoading: Boolean = false
)