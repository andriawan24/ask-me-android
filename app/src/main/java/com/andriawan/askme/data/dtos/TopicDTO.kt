package com.andriawan.askme.data.dtos

import com.andriawan.askme.domain.models.TopicModel
import com.andriawan.askme.utils.extensions.orZero
import com.google.gson.annotations.SerializedName

data class TopicDTO(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("image_url")
    val imageURL: String? = null
)

fun TopicDTO?.toModel(): TopicModel = TopicModel(
    id = this?.id.orZero(),
    name = this?.name.orEmpty(),
    description = this?.description.orEmpty(),
    imageURL = this?.imageURL.orEmpty()
)

fun List<TopicDTO>.toModels(): List<TopicModel> = map { it.toModel() }