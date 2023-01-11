package com.andriawan.askme.data.repository

import com.andriawan.askme.data.dtos.toModel
import com.andriawan.askme.data.network.AskMeAPI
import com.andriawan.askme.domain.models.QuestionModel
import com.andriawan.askme.domain.repository.QuestionRepository
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(private val api: AskMeAPI) : QuestionRepository {

    override suspend fun getQuestions(): List<QuestionModel> {
        return api.getQuestions().data?.map { it.toModel() }.orEmpty()
    }
}