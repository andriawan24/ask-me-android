package com.andriawan.askme.domain.models

data class UserModel(
    val createdAt: String,
    val email: String,
    val id: Int,
    val imageUrl: String?,
    val name: String,
    val phoneNumber: String?,
    val points: Int,
    val updatedAt: String?
)