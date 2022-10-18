package com.andriawan.askme.domain.usecases.topic

import com.andriawan.askme.domain.models.TopicModel
import com.andriawan.askme.domain.repository.TopicRepository
import com.andriawan.askme.utils.FlowableUseCase
import com.andriawan.askme.utils.None
import com.andriawan.askme.utils.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTopicUseCase(
    private val repository: TopicRepository
): FlowableUseCase<None, List<TopicModel>> {

    override fun execute(param: None): Flow<ResultState<List<TopicModel>>> = flow {
        emit(ResultState.Loading())
        try {
            val result = repository.getTopics()
            emit(ResultState.Success(result))
        } catch (e: Exception) {
            emit(ResultState.Error(e))
        }
    }
}