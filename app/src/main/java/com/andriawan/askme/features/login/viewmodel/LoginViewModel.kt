package com.andriawan.askme.features.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andriawan.askme.data.dtos.LoginDTO
import com.andriawan.askme.domain.usecases.auth.SignInUseCase
import com.andriawan.askme.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
): ViewModel() {

    private val _signInResult = MutableLiveData<ResultState<LoginDTO>>()
    val signInResult: LiveData<ResultState<LoginDTO>> = _signInResult

    fun signIn(email: String, password: String) {
        val param = SignInUseCase.Param(email, password)

        viewModelScope.launch {
            signInUseCase.execute(param).collectLatest { _signInResult.value = it }
        }
    }
}