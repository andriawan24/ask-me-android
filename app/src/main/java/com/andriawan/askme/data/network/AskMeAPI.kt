package com.andriawan.askme.data.network

import com.andriawan.askme.data.dtos.LoginDTO
import com.andriawan.askme.data.dtos.PostLoginBody
import com.andriawan.askme.data.dtos.UserDTO
import com.andriawan.askme.utils.Constants.GET_CREDENTIAL_SERVICE
import com.andriawan.askme.utils.Constants.SIGN_IN_SERVICE
import com.andriawan.askme.utils.network.BaseResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AskMeAPI {

    @POST(SIGN_IN_SERVICE)
    suspend fun signIn(@Body postLoginBody: PostLoginBody): BaseResponse<LoginDTO>

    @GET(GET_CREDENTIAL_SERVICE)
    suspend fun getCredential(): BaseResponse<UserDTO>
}