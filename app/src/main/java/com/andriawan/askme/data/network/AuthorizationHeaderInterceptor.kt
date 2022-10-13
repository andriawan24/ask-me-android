package com.andriawan.askme.data.network

import android.content.Context
import com.andriawan.askme.utils.Constants.ACCESS_TOKEN_KEY
import com.andriawan.askme.utils.Constants.AUTHORIZATION_HEADER
import com.andriawan.askme.utils.Constants.BEARER_KEY
import com.andriawan.askme.utils.SharedPreferencesHelper
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationHeaderInterceptor(
    private val context: Context
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain
            .request()
            .newBuilder()

        if (SharedPreferencesHelper.getString(context, ACCESS_TOKEN_KEY).isNotEmpty()) {
            newRequest.addHeader(
                AUTHORIZATION_HEADER,
                "$BEARER_KEY ${SharedPreferencesHelper.getString(context, ACCESS_TOKEN_KEY)}"
            )
        }

        return chain.proceed(newRequest.build())
    }
}