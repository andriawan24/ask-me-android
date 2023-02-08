package com.andriawan.askme.domain.repository

import com.andriawan.askme.domain.models.QuestionModel

interface QuestionRepository {
    suspend fun getQuestions(query: String): List<QuestionModel>
}