package com.andriawan.askme.data.dtos

import com.andriawan.askme.domain.models.LoginBodyModel
import com.google.gson.annotations.SerializedName

data class LoginBodyDTO(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
) {
    companion object {
        fun from(model: LoginBodyModel) : LoginBodyDTO {
            return LoginBodyDTO(
                email = model.email,
                password = model.password
            )
        }
    }
}
