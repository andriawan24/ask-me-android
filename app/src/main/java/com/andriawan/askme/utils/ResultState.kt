package com.andriawan.askme.utils

import androidx.annotation.StringRes

sealed class ResultState<T> {
    class Loading<T> : ResultState<T>()
    data class Success<T>(val data: T?) : ResultState<T>()
    data class Error<T>(val message: String, val data: T? = null) : ResultState<T>()
    data class ErrorGeneric<T>(@StringRes val message: Int): ResultState<T>()
}
