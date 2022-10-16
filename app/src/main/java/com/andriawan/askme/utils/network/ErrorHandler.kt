package com.andriawan.askme.utils.network

import android.content.Context
import com.andriawan.askme.R
import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException

fun Exception.getErrorMessage(context: Context): String {
    when (this) {
        is IOException -> {
            return context.getString(R.string.no_internet)
        }

        is HttpException -> {
            val errorResponse = Gson().fromJson(
                this.response()!!.errorBody()!!.charStream(),
                BaseResponse::class.java
            )
            return errorResponse.message
        }

        else -> {
            return this.message.toString()
        }
    }
}
