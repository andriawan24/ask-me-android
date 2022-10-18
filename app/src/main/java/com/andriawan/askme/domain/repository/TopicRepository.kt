package com.andriawan.askme.domain.repository

import com.andriawan.askme.domain.models.TopicModel

interface TopicRepository {
    suspend fun getTopics(): List<TopicModel>
}