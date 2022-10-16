package com.andriawan.askme.utils

sealed class ResultState<T> {
    class Loading<T> : ResultState<T>()
    data class Success<T>(val data: T?) : ResultState<T>()
    data class Error<T>(val exception: Exception, val data: T? = null) : ResultState<T>()
}
