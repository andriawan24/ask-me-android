package com.andriawan.askme.domain.repository

import com.andriawan.askme.domain.models.LoginBodyModel
import com.andriawan.askme.domain.models.RegisterBodyModel
import com.andriawan.askme.domain.models.UserModel

interface AuthRepository {
    suspend fun signIn(loginBody: LoginBodyModel): String
    suspend fun getCredentials(): UserModel?
    suspend fun signUp(registerBody: RegisterBodyModel): UserModel?
    suspend fun saveCredentials(userModel: UserModel?)
    suspend fun getUser(): UserModel?
}