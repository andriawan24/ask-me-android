package com.andriawan.askme.data.dtos

import com.google.gson.annotations.SerializedName

data class LoginDTO(
    @SerializedName("access_token")
    val accessToken: String
)
