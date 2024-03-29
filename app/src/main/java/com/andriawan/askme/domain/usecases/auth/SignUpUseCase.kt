package com.andriawan.askme.domain.usecases.auth

import com.andriawan.askme.domain.models.RegisterBodyModel
import com.andriawan.askme.domain.models.UserModel
import com.andriawan.askme.domain.repository.AuthRepository
import com.andriawan.askme.utils.FlowableUseCase
import com.andriawan.askme.utils.ResultState
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class SignUpUseCase @Inject constructor(
    private val repository: AuthRepository
) : FlowableUseCase<SignUpUseCase.Param, UserModel?> {

    override fun execute(param: Param): Flow<ResultState<UserModel?>> = flow {
        emit(ResultState.Loading())
        try {
            val result = repository.signUp(
                RegisterBodyModel(
                    name = param.name,
                    email = param.email,
                    password = param.password
                )
            )
            emit(ResultState.Success(result))
        } catch (e: Exception) {
            emit(ResultState.Error(e))
        }
    }

    class Param(
        val name: String,
        val email: String,
        val password: String
    )
}