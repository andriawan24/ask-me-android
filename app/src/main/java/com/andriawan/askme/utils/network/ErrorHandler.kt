package com.andriawan.askme.utils.network

import android.content.Context
import com.andriawan.askme.R
import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException

fun Exception.getError(context: Context): String {
    return when (this) {
        is IOException -> {
            context.getString(R.string.no_internet)
        }

        is HttpException -> {
            try {
                val errorResponse = Gson().fromJson(
                    this.response()?.errorBody()?.charStream(),
                    BaseResponse::class.java
                )
                errorResponse.message
            } catch (e: Exception) {
                e.message.toString()
            }
        }

        else -> {
            this.message.toString()
        }
    }
}