package com.andriawan.askme.domain.models

import com.andriawan.askme.data.dtos.QuestionStatus

data class QuestionModel(
    val id: Int,
    val title: String,
    val text: String,
    val upVote: Int,
    val downVote: Int,
    val status: QuestionStatus,
    val answersCount: Int,
    val createdAt: String,
    val topic: TopicModel,
    val user: UserModel
)