package com.andriawan.askme.domain.usecases.auth

import com.andriawan.askme.domain.models.UserModel
import com.andriawan.askme.domain.repository.AuthRepository
import com.andriawan.askme.utils.FlowableUseCase
import com.andriawan.askme.utils.None
import com.andriawan.askme.utils.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCredentialUseCase(
    private val repository: AuthRepository
): FlowableUseCase<None, UserModel?> {

    override fun execute(param: None): Flow<ResultState<UserModel?>> = flow {
        emit(ResultState.Loading())
        try {
            val result = repository.getCredentials()
            emit(ResultState.Success(result))
        } catch (e: Exception) {
            emit(ResultState.Error(e))
        }
    }
}