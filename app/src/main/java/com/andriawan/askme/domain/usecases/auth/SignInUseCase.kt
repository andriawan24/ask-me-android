package com.andriawan.askme.domain.usecases.auth

import com.andriawan.askme.domain.models.LoginBodyModel
import com.andriawan.askme.domain.repository.AuthRepository
import com.andriawan.askme.utils.Constants
import com.andriawan.askme.utils.FlowableUseCase
import com.andriawan.askme.utils.ResultState
import com.andriawan.askme.utils.SharedPreferencesHelper
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class SignInUseCase @Inject constructor(
    private val repository: AuthRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : FlowableUseCase<SignInUseCase.Param, String?> {

    override fun execute(param: Param): Flow<ResultState<String?>> = flow {
        emit(ResultState.Loading())
        try {
            val body = LoginBodyModel(email = param.email, password = param.password)
            val result = repository.signIn(body)
            sharedPreferencesHelper.saveString(Constants.ACCESS_TOKEN_KEY, result)
            repository.getCredentials()
            emit(ResultState.Success(result))
        } catch (e: Exception) {
            emit(ResultState.Error(e))
        }
    }

    data class Param(
        val email: String,
        val password: String
    )
}