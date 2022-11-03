package com.andriawan.askme.utils

import androidx.compose.ui.unit.dp
import com.andriawan.askme.R
import java.util.*

object Constants {
    const val NEW_LINE = "\n"
    const val SPACE = " "
    const val EMPTY = ""
    const val ZERO = 0
    const val ONE = 1
    const val TWO = 2
    const val COMMA_WITH_SPACE = ", "
    const val MINUS_ONE = -1

    const val MINIMUM_PASSWORD_LENGTH = 8
    const val MAXIMUM_PASSWORD_LENGTH = 14

    val DEFAULT_MARGIN = 24.dp

    // Network
    const val TIMEOUT_DEFAULT = 30L
    const val AUTHORIZATION_HEADER = "Authorization"
    const val BEARER_KEY = "Bearer"
    const val ACCESS_TOKEN_KEY = "access_token"
    const val UNAUTHORIZED = "unauthorized"

    // Alert Types
    enum class AlertType {
        WARNING,
        SUCCESS,
        ERROR
    }

    fun getGreetingsTextResource(): Int {
        val calendar = Calendar.getInstance()
        val hours = calendar.get(Calendar.HOUR_OF_DAY)
        return when {
            hours < 12 -> R.string.morning_greetings
            hours < 17 -> R.string.afternoon_greetings
            hours < 20 -> R.string.evening_greetings
            else -> R.string.night_greetings
        }
    }
}