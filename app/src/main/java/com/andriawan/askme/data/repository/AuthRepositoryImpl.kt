package com.andriawan.askme.data.repository

import com.andriawan.askme.data.dtos.PostLoginBody
import com.andriawan.askme.data.dtos.PostRegisterBody
import com.andriawan.askme.data.dtos.toModel
import com.andriawan.askme.data.network.AskMeAPI
import com.andriawan.askme.domain.models.UserModel
import com.andriawan.askme.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AskMeAPI
): AuthRepository {

    override suspend fun signIn(postLoginBody: PostLoginBody): String? {
        val response = api.signIn(postLoginBody)
        return response.data?.accessToken
    }

    override suspend fun getCredentials(): UserModel? {
        val credentialResponse = api.getCredential()
        return credentialResponse.data?.toModel()
    }

    override suspend fun signUp(postRegisterBody: PostRegisterBody): UserModel? {
        val credentialResponse = api.signUp(postRegisterBody)
        return credentialResponse.data?.toModel()
    }
}