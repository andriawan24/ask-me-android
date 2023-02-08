package com.andriawan.askme.ui.screens.questions.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andriawan.askme.domain.usecases.questions.GetQuestionsUseCase
import com.andriawan.askme.ui.screens.questions.models.QuestionUiState
import com.andriawan.askme.utils.None
import com.andriawan.askme.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val getQuestionsUseCase: GetQuestionsUseCase
) : ViewModel() {

    var uiState by mutableStateOf(QuestionUiState())
        private set

    private val _message = MutableSharedFlow<Exception>()
    val message = _message.asSharedFlow()

    fun updateQuery(query: String) {
        uiState = uiState.copy(
            query = query
        )
    }

    fun initData() {
        viewModelScope.launch {
            val param = GetQuestionsUseCase.Param(query = uiState.query)
            getQuestionsUseCase.execute(param).collectLatest {
                when (it) {
                    is ResultState.Loading -> {
                        uiState = uiState.copy(
                            isLoading = true
                        )
                    }
                    is ResultState.Success -> {
                        uiState = uiState.copy(
                            isLoading = false,
                            questions = it.data.orEmpty()
                        )
                    }
                    is ResultState.Error -> {
                        uiState = uiState.copy(isLoading = false)
                        _message.emit(it.exception)
                    }
                }
            }
        }
    }
}