package com.andriawan.askme.ui.screens.register.models

import androidx.annotation.StringRes
import com.andriawan.askme.utils.Constants
import com.andriawan.askme.utils.Constants.EMPTY

data class RegisterUiState(
    val name: String = EMPTY,
    val email: String = EMPTY,
    val password: String = EMPTY,
    val passwordConfirmation: String = EMPTY,
    @StringRes val nameError: Int = Constants.MINUS_ONE,
    @StringRes val emailError: Int = Constants.MINUS_ONE,
    @StringRes val passwordError: Int = Constants.MINUS_ONE,
    @StringRes val passwordConfirmationError: Int = Constants.MINUS_ONE,
    val passwordVisible: Boolean = false,
    val passwordConfirmationVisible: Boolean = false,
    val registerButtonEnabled: Boolean = false
)