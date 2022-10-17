package com.andriawan.askme.utils.extensions

import android.widget.Button
import androidx.annotation.StringRes
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.setErrorText(message: String) {
    this.isErrorEnabled = true
    this.error = message
}

fun TextInputLayout.setErrorText(message: Int?) {
    if (message != null) {
        this.isErrorEnabled = true
        this.error = this.context.getString(message)
    } else {
        this.isErrorEnabled = false
    }
}

fun Button.disabledWithText(@StringRes text: Int) {
    this.isEnabled = false
    this.text = this.context.getString(text)
}

fun Button.enableWithText(@StringRes text: Int) {
    this.isEnabled = true
    this.text = this.context.getString(text)
}