package com.andriawan.askme.data.dtos

import com.andriawan.askme.domain.models.UserModel
import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone_number")
    val phoneNumber: String?,
    @SerializedName("points")
    val points: Int,
    @SerializedName("updated_at")
    val updatedAt: String?
)

fun UserDTO.toModel(): UserModel {
    return UserModel(
        this.createdAt,
        this.email,
        this.id,
        this.imageUrl,
        this.name,
        this.phoneNumber,
        this.points,
        this.updatedAt
    )
}
