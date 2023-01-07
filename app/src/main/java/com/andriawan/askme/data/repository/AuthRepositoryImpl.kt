package com.andriawan.askme.data.repository

import com.andriawan.askme.data.dtos.LoginBodyDTO
import com.andriawan.askme.data.dtos.RegisterBodyDTO
import com.andriawan.askme.data.dtos.toModel
import com.andriawan.askme.data.local.datastore.AskMeDataStore
import com.andriawan.askme.data.network.AskMeAPI
import com.andriawan.askme.domain.models.LoginBodyModel
import com.andriawan.askme.domain.models.RegisterBodyModel
import com.andriawan.askme.domain.models.UserModel
import com.andriawan.askme.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AskMeAPI,
    private val dataStore: AskMeDataStore
) : AuthRepository {

    override suspend fun signIn(loginBody: LoginBodyModel): String {
        val response = api.signIn(LoginBodyDTO.from(loginBody))
        return response.data?.accessToken.orEmpty()
    }

    override suspend fun getCredentials(): UserModel? {
        val credentialResponse = api.getCredential()
        dataStore.saveUser(credentialResponse.data?.toModel())
        return credentialResponse.data?.toModel()
    }

    override suspend fun signUp(registerBody: RegisterBodyModel): UserModel? {
        val credentialResponse = api.signUp(RegisterBodyDTO.from(registerBody))
        return credentialResponse.data?.toModel()
    }

    override suspend fun saveCredentials(userModel: UserModel?) {
        dataStore.saveUser(userModel)
    }
}