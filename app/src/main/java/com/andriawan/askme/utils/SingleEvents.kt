package com.andriawan.askme.utils

class SingleEvents<T>(private val data: T) {

    private var isHandled = false

    fun getContentIfNotHandled(): T? {
        return if (!isHandled) {
            isHandled = true
            data
        } else null
    }
}