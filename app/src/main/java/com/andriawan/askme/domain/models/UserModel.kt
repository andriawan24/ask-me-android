package com.andriawan.askme.domain.models

data class UserModel(
    val id: Int,
    val name: String,
    val email: String,
    val phoneNumber: String,
    val points: Int,
    val imageUrl: String,
    val createdAt: String,
    val updatedAt: String
)