package com.andriawan.askme.ui.screens.questions.models

import com.andriawan.askme.domain.models.QuestionModel
import com.andriawan.askme.utils.Constants.EMPTY

data class QuestionUiState(
    val isLoading: Boolean = false,
    val questions: List<QuestionModel> = emptyList(),
    val query: String = EMPTY
)