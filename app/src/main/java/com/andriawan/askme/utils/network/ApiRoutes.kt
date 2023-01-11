package com.andriawan.askme.utils.network

/**
 * Used to define API routes and use it in [com.andriawan.askme.data.network.AskMeAPI] interface
 */
object ApiRoutes {
    const val SIGN_IN_SERVICE = "/v1/api/auth/sign-in"
    const val SIGN_UP_SERVICE = "/v1/api/auth/sign-up"
    const val GET_CREDENTIAL_SERVICE = "/v1/api/auth/me"
    const val GET_TOPICS = "/v1/api/topics"
    const val GET_QUESTIONS = "/v1/api/questions"
}
