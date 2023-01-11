package com.andriawan.askme.data.dtos

import com.andriawan.askme.domain.models.QuestionModel
import com.andriawan.askme.utils.extensions.orZero
import com.google.gson.annotations.SerializedName

data class QuestionDTO(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("text")
    val text: String? = null,
    @SerializedName("upvote")
    val upVote: Int? = null,
    @SerializedName("downvote")
    val downVote: Int? = null,
    @SerializedName("status")
    val status: QuestionStatus? = null,
    @SerializedName("answers_count")
    val answersCount: Int? = null,
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("topic")
    val topic: TopicDTO? = null,
    @SerializedName("user")
    val user: UserDTO
)

enum class QuestionStatus {
    @SerializedName("proccess")
    PROCESS,

    @SerializedName("answered")
    ANSWERED,

    @SerializedName("deleted")
    DELETED
}

fun QuestionDTO.toModel(): QuestionModel {
    return QuestionModel(
        id = id.orZero(),
        title = title.orEmpty(),
        text = text.orEmpty(),
        upVote = upVote.orZero(),
        downVote = downVote.orZero(),
        status = status ?: QuestionStatus.PROCESS,
        answersCount = answersCount.orZero(),
        createdAt = createdAt.orEmpty(),
        topic = topic.toModel(),
        user = user.toModel()
    )
}