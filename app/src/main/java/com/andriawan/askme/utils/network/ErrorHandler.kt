package com.andriawan.askme.utils.network

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.andriawan.askme.R
import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException

@Composable
fun getErrorMessage(exception: Exception): String {
    return when (exception) {
        is IOException -> {
            stringResource(R.string.no_internet)
        }

        is HttpException -> {
            val errorResponse = Gson().fromJson(
                exception.response()!!.errorBody()!!.charStream(),
                BaseResponse::class.java
            )
            errorResponse.message
        }

        else -> {
            exception.message.toString()
        }
    }
}
