package com.andriawan.askme.ui.screens.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andriawan.askme.domain.usecases.auth.GetUserUseCase
import com.andriawan.askme.domain.usecases.auth.SignOutUseCase
import com.andriawan.askme.domain.usecases.topic.GetTopicUseCase
import com.andriawan.askme.ui.screens.home.models.HomeUiState
import com.andriawan.askme.utils.None
import com.andriawan.askme.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getTopicUseCase: GetTopicUseCase,
    private val signOutUseCase: SignOutUseCase
) : ViewModel() {

    var uiState by mutableStateOf(HomeUiState())
        private set

    private val _showErrorMessage = MutableSharedFlow<Exception>()
    val showErrorMessage = _showErrorMessage.asSharedFlow()

    private val _navigateToLogin = MutableSharedFlow<None>()
    val navigateToLogin = _navigateToLogin.asSharedFlow()

    init {
        viewModelScope.launch {
            getUserUseCase.execute(None).collectLatest {
                uiState = when (it) {
                    is ResultState.Loading -> uiState.copy(loadingUserModel = true)
                    is ResultState.Success -> uiState.copy(loadingUserModel = false, user = it.data)
                    is ResultState.Error -> {
                        _showErrorMessage.emit(it.exception)
                        uiState.copy(loadingUserModel = false)
                    }
                }
            }
            getTopicUseCase.execute(None).collectLatest {
                uiState = when (it) {
                    is ResultState.Loading -> uiState.copy(loadingUserModel = true)
                    is ResultState.Success -> uiState.copy(loadingTopics = false, topics = it.data)
                    is ResultState.Error -> {
                        _showErrorMessage.emit(it.exception)
                        uiState.copy(loadingTopics = false)
                    }
                }
            }
        }
    }

    fun onUnauthorizedRequest() {
        viewModelScope.launch {
            signOutUseCase.execute(None).collectLatest {
                if (it is ResultState.Success) {
                    _navigateToLogin.emit(None)
                }
            }
        }
    }
}