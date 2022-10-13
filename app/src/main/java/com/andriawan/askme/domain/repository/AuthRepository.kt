package com.andriawan.askme.domain.repository

import com.andriawan.askme.data.dtos.LoginDTO
import com.andriawan.askme.data.dtos.PostLoginBody
import com.andriawan.askme.data.dtos.UserDTO
import com.andriawan.askme.utils.network.BaseResponse

interface AuthRepository {
    suspend fun signIn(postLoginBody: PostLoginBody): BaseResponse<LoginDTO>
    suspend fun getCredentials(): BaseResponse<UserDTO>
}