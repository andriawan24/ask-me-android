package com.andriawan.askme.domain.usecases.auth

import com.andriawan.askme.data.dtos.LoginDTO
import com.andriawan.askme.data.dtos.PostLoginBody
import com.andriawan.askme.domain.repository.AuthRepository
import com.andriawan.askme.utils.FlowableUseCase
import com.andriawan.askme.utils.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@SuppressWarnings("TooGenericExceptionCaught", "SwallowedException")
class SignInUseCase(
    private val repository: AuthRepository
) : FlowableUseCase<SignInUseCase.Param, LoginDTO> {

    override fun execute(param: Param): Flow<ResultState<LoginDTO>> = flow {
        emit(ResultState.Loading())
        try {
            val body = PostLoginBody(email = param.email, password = param.password)
            val result = repository.signIn(body)
            emit(ResultState.Success(result.data))
        } catch (e: Exception) {
            emit(ResultState.Error(e))
        }
    }

    data class Param(
        val email: String,
        val password: String
    )
}