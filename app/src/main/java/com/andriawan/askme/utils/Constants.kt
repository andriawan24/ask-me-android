package com.andriawan.askme.utils

object Constants {
    const val NEW_LINE = "\n"
    const val SPACE = " "
    const val EMPTY = ""
    const val ZERO = 0
    const val ONE = 1

    const val MINIMUM_PASSWORD_LENGTH = 8
    const val MAXIMUM_PASSWORD_LENGTH = 14

    // Network
    const val TIMEOUT_DEFAULT = 30L
    const val AUTHORIZATION_HEADER = "Authorization"
    const val BEARER_KEY = "Bearer"
    const val ACCESS_TOKEN_KEY = "access_token"

    // API Services
    const val SIGN_IN_SERVICE = "/v1/api/auth/sign-in"
    const val SIGN_UP_SERVICE = "/v1/api/auth/sign-up"
    const val GET_CREDENTIAL_SERVICE = "/v1/api/auth/me"

    // Alert Types
    enum class AlertType {
        WARNING,
        SUCCESS,
        ERROR
    }
}