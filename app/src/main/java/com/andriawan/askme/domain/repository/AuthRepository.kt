package com.andriawan.askme.domain.repository

import com.andriawan.askme.data.dtos.PostLoginBodyDTO
import com.andriawan.askme.data.dtos.PostRegisterBodyDTO
import com.andriawan.askme.domain.models.UserModel

interface AuthRepository {
    suspend fun signIn(postLoginBody: PostLoginBodyDTO): String?
    suspend fun getCredentials(): UserModel?
    suspend fun signUp(postRegisterBody: PostRegisterBodyDTO): UserModel?
    suspend fun saveCredentials(userModel: UserModel?)
}