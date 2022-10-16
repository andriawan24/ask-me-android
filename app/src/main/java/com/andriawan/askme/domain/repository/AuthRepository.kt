package com.andriawan.askme.domain.repository

import com.andriawan.askme.data.dtos.PostLoginBody
import com.andriawan.askme.domain.models.UserModel

interface AuthRepository {
    suspend fun signIn(postLoginBody: PostLoginBody): String?
    suspend fun getCredentials(): UserModel?
}