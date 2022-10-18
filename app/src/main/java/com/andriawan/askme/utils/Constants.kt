package com.andriawan.askme.utils

import android.content.Context
import com.andriawan.askme.R
import java.util.Calendar

object Constants {
    const val NEW_LINE = "\n"
    const val SPACE = " "
    const val EMPTY = ""
    const val ZERO = 0
    const val ONE = 1
    const val TWO = 2
    const val COMMA_WITH_SPACE = ", "

    const val MINIMUM_PASSWORD_LENGTH = 8
    const val MAXIMUM_PASSWORD_LENGTH = 14

    const val DEFAULT_MARGIN = 24

    fun Context.dip(value: Int): Int = (value * resources.displayMetrics.density).toInt()
    fun Context.dip(value: Float): Int = (value * resources.displayMetrics.density).toInt()

    // Network
    const val TIMEOUT_DEFAULT = 30L
    const val AUTHORIZATION_HEADER = "Authorization"
    const val BEARER_KEY = "Bearer"
    const val ACCESS_TOKEN_KEY = "access_token"
    const val UNAUTHORIZED = "unauthorized"

    // API Services
    const val SIGN_IN_SERVICE = "/v1/api/auth/sign-in"
    const val SIGN_UP_SERVICE = "/v1/api/auth/sign-up"
    const val GET_CREDENTIAL_SERVICE = "/v1/api/auth/me"
    const val GET_TOPICS = "/v1/api/topics"

    // Alert Types
    enum class AlertType {
        WARNING,
        SUCCESS,
        ERROR
    }

    fun getGreetings(): Int {
        val calendar = Calendar.getInstance()
        val hours = calendar.get(Calendar.HOUR_OF_DAY)

        return when {
            hours < 12 -> {
                R.string.morning_greetings
            }

            hours < 17 -> {
                R.string.afternoon_greetings
            }

            hours < 20 -> {
                R.string.evening_greetings
            }

            else -> {
                R.string.night_greetings
            }
        }
    }
}