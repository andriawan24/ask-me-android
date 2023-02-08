package com.andriawan.askme.domain.usecases.questions

import com.andriawan.askme.domain.models.QuestionModel
import com.andriawan.askme.domain.repository.QuestionRepository
import com.andriawan.askme.utils.Constants.EMPTY
import com.andriawan.askme.utils.FlowableUseCase
import com.andriawan.askme.utils.None
import com.andriawan.askme.utils.ResultState
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class GetQuestionsUseCase @Inject constructor(
    private val repository: QuestionRepository
) : FlowableUseCase<GetQuestionsUseCase.Param, List<QuestionModel>> {

    override fun execute(param: Param): Flow<ResultState<List<QuestionModel>>> = flow {
        emit(ResultState.Loading())
        try {
            val result = repository.getQuestions(param.query)
            emit(ResultState.Success(result))
        } catch (e: Exception) {
            emit(ResultState.Error(e))
        }
    }

    data class Param(
        val query: String = EMPTY
    )
}