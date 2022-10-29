package com.andriawan.askme.data.dtos

import com.google.gson.annotations.SerializedName

data class PostLoginBodyDTO(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)
