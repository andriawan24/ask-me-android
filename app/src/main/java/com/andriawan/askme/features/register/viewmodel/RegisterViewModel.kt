package com.andriawan.askme.features.register.viewmodel

import android.text.Editable
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andriawan.askme.R
import com.andriawan.askme.domain.models.UserModel
import com.andriawan.askme.domain.usecases.auth.SignUpUseCase
import com.andriawan.askme.utils.Constants
import com.andriawan.askme.utils.ResultState
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _signUpResponse = MutableLiveData<ResultState<UserModel?>>()
    val signUpResponse: LiveData<ResultState<UserModel?>> = _signUpResponse

    fun signUp(name: String, email: String, password: String) {
        val param = SignUpUseCase.Param(
            name = name,
            email = email,
            password = password
        )
        viewModelScope.launch {
            signUpUseCase.execute(param).collectLatest { _signUpResponse.value = it }
        }
    }

    fun validateName(name: Editable?): Int? {
        return if (name.isNullOrBlank()) {
            R.string.empty_name
        } else {
            null
        }
    }

    fun validateEmail(email: Editable?): Int? {
        return if (email.isNullOrBlank()) {
            R.string.empty_email
        } else if (!Pattern.matches(Patterns.EMAIL_ADDRESS.pattern(), email)) {
            R.string.not_valid_email
        } else {
            null
        }
    }

    fun validatePassword(password: Editable?): Int? {
        return if (password.isNullOrBlank()) {
            R.string.empty_password
        } else if (password.length !in Constants.MINIMUM_PASSWORD_LENGTH..Constants.MAXIMUM_PASSWORD_LENGTH) {
            R.string.length_password
        } else {
            null
        }
    }

    fun validatePasswordConfirmation(password: Editable?, passwordConfirmation: Editable?): Int? {
        return if (password.toString() != passwordConfirmation.toString()) {
            R.string.invalid_password_confirmation
        } else {
            null
        }
    }
}