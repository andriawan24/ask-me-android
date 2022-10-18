package com.andriawan.askme.data.repository

import com.andriawan.askme.data.dtos.toModels
import com.andriawan.askme.data.network.AskMeAPI
import com.andriawan.askme.domain.models.TopicModel
import com.andriawan.askme.domain.repository.TopicRepository
import javax.inject.Inject

class TopicRepositoryImpl @Inject constructor(
    private val api: AskMeAPI
): TopicRepository {

    override suspend fun getTopics(): List<TopicModel> {
        val topics = api.getTopics()
        return topics.data?.toModels().orEmpty()
    }
}