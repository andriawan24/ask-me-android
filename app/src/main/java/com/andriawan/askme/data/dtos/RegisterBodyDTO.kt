package com.andriawan.askme.data.dtos

import com.andriawan.askme.domain.models.RegisterBodyModel
import com.google.gson.annotations.SerializedName

data class RegisterBodyDTO(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("password")
    val password: String? = null
) {
    companion object {
        fun from(model: RegisterBodyModel) : RegisterBodyDTO {
            return RegisterBodyDTO(
                name = model.name,
                email = model.email,
                password = model.password
            )
        }
    }
}