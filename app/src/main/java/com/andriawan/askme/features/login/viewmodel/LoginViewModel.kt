package com.andriawan.askme.features.login.viewmodel

import android.text.Editable
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andriawan.askme.R
import com.andriawan.askme.domain.usecases.auth.GetCredentialUseCase
import com.andriawan.askme.domain.usecases.auth.SignInUseCase
import com.andriawan.askme.utils.Constants
import com.andriawan.askme.utils.Constants.EMPTY
import com.andriawan.askme.utils.None
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

    init {
        if (sharedPreferencesHelper.getString(Constants.ACCESS_TOKEN_KEY).isNotEmpty()) {
            _signInResult.value = ResultState.Success(EMPTY)
        }
    }

    fun signIn(email: String, password: String) {
        val param = SignInUseCase.Param(email, password)

        viewModelScope.launch {
            signInUseCase.execute(param).collectLatest { _signInResult.value = it }
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
        } else {
            null
        }
    }
}