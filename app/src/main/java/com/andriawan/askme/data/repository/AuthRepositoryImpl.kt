package com.andriawan.askme.data.repository

import com.andriawan.askme.data.dtos.LoginDTO
import com.andriawan.askme.data.dtos.PostLoginBody
import com.andriawan.askme.data.dtos.UserDTO
import com.andriawan.askme.data.network.AskMeAPI
import com.andriawan.askme.domain.repository.AuthRepository
import com.andriawan.askme.utils.network.BaseResponse
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AskMeAPI
): AuthRepository {

    override suspend fun signIn(postLoginBody: PostLoginBody): BaseResponse<LoginDTO> {
        return api.signIn(postLoginBody)
    }

    override suspend fun getCredentials(): BaseResponse<UserDTO> {
        return api.getCredential()
    }
}