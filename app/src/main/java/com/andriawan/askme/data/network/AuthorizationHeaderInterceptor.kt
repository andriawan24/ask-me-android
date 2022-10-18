package com.andriawan.askme.data.network

import com.andriawan.askme.utils.Constants.ACCESS_TOKEN_KEY
import com.andriawan.askme.utils.Constants.AUTHORIZATION_HEADER
import com.andriawan.askme.utils.Constants.BEARER_KEY
import com.andriawan.askme.utils.SharedPreferencesHelper
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationHeaderInterceptor(
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain
            .request()
            .newBuilder()

        if (sharedPreferencesHelper.getString(ACCESS_TOKEN_KEY).isNotEmpty()) {
            newRequest.addHeader(
                AUTHORIZATION_HEADER,
                "$BEARER_KEY ${sharedPreferencesHelper.getString(ACCESS_TOKEN_KEY)}"
            )
        }

        return chain.proceed(newRequest.build())
    }
}