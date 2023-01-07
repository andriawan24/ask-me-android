package com.andriawan.askme.domain.models

data class RegisterBodyModel(
    val name: String,
    val email: String,
    val password: String
)