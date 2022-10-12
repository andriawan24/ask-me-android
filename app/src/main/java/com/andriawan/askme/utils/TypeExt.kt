package com.andriawan.askme.utils

fun Boolean?.orFalse() = this ?: false
fun Boolean?.orTrue() = this ?: true