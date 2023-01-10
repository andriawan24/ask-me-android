package com.andriawan.askme.utils.extensions

import com.andriawan.askme.utils.Constants
import com.andriawan.askme.utils.Constants.ZERO

fun Boolean?.orFalse() = this ?: false
fun Boolean?.orTrue() = this ?: true
fun Int?.orZero() = this ?: ZERO
fun Int?.orDefault(defaultValue: Int) = this ?: defaultValue
fun String?.getFirstWord() = this.orEmpty().split(Constants.SPACE).first()