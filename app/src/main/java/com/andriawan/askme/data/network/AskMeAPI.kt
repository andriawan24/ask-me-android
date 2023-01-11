package com.andriawan.askme.data.network

import com.andriawan.askme.data.dtos.*
import com.andriawan.askme.utils.network.ApiRoutes.GET_CREDENTIAL_SERVICE
import com.andriawan.askme.utils.network.ApiRoutes.GET_QUESTIONS
import com.andriawan.askme.utils.network.ApiRoutes.GET_TOPICS
import com.andriawan.askme.utils.network.ApiRoutes.SIGN_IN_SERVICE
import com.andriawan.askme.utils.network.ApiRoutes.SIGN_UP_SERVICE
import com.andriawan.askme.utils.network.BaseResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AskMeAPI {

    @POST(SIGN_IN_SERVICE)
    suspend fun signIn(@Body postLoginBody: LoginBodyDTO): BaseResponse<LoginDTO>

    @GET(GET_CREDENTIAL_SERVICE)
    suspend fun getCredential(): BaseResponse<UserDTO>

    @POST(SIGN_UP_SERVICE)
    suspend fun signUp(@Body postRegisterBody: RegisterBodyDTO): BaseResponse<UserDTO>

    @GET(GET_TOPICS)
    suspend fun getTopics(): BaseResponse<List<TopicDTO>>

    @GET(GET_QUESTIONS)
    suspend fun getQuestions(): BaseResponse<List<QuestionDTO>>
}