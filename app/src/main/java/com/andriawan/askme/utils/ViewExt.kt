package com.andriawan.askme.utils

import android.widget.Button
import androidx.annotation.StringRes
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.setErrorText(message: String) {
    this.isErrorEnabled = true
    this.error = message
}

fun Button.disabledWithText(@StringRes text: Int) {
    this.isEnabled = false
    this.text = this.context.getString(text)
}

fun Button.enableWithText(@StringRes text: Int) {
    this.isEnabled = true
    this.text = this.context.getString(text)
}