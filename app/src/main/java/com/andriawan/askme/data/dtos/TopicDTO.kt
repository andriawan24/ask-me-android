package com.andriawan.askme.data.dtos

import com.andriawan.askme.domain.models.TopicModel
import com.google.gson.annotations.SerializedName

data class TopicDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image_url")
    val imageURL: String? = null
)

fun TopicDTO.toModel(): TopicModel = TopicModel(
    this.id,
    this.name,
    this.description,
    this.imageURL
)

fun List<TopicDTO>.toModels(): List<TopicModel> = this.map { it.toModel() }