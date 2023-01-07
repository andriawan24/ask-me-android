package com.andriawan.askme.data.dtos

import com.andriawan.askme.domain.models.UserModel
import com.andriawan.askme.utils.extensions.orZero
import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("image_url")
    val imageUrl: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("phone_number")
    val phoneNumber: String? = null,
    @SerializedName("points")
    val points: Int? = null,
    @SerializedName("updated_at")
    val updatedAt: String? = null
)

fun UserDTO.toModel(): UserModel {
    return UserModel(
        id = this.id.orZero(),
        email = this.email.orEmpty(),
        name = this.name.orEmpty(),
        imageUrl = this.imageUrl.orEmpty(),
        phoneNumber = this.phoneNumber.orEmpty(),
        points = this.points.orZero(),
        createdAt = this.createdAt.orEmpty(),
        updatedAt = this.updatedAt.orEmpty()
    )
}
