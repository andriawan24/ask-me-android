package com.andriawan.askme.utils.network

import android.content.Context
import com.andriawan.askme.R
import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException

fun Exception.getErrorMessage(context: Context): String {
    return when (this) {
        is IOException -> {
            context.getString(R.string.no_internet)
        }

        is HttpException -> {
            val errorResponse = Gson().fromJson(
                this.response()!!.errorBody()!!.charStream(),
                BaseResponse::class.java
            )
            errorResponse.message
        }

        else -> {
            this.message.toString()
        }
    }
}
