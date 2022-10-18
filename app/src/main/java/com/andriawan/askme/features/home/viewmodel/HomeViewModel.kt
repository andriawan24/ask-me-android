package com.andriawan.askme.features.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andriawan.askme.domain.models.TopicModel
import com.andriawan.askme.domain.models.UserModel
import com.andriawan.askme.domain.usecases.auth.GetCredentialUseCase
import com.andriawan.askme.domain.usecases.topic.GetTopicUseCase
import com.andriawan.askme.utils.None
import com.andriawan.askme.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCredentialUseCase: GetCredentialUseCase,
    private val getTopicUseCase: GetTopicUseCase
) : ViewModel() {

    private val _userModel = MutableLiveData<ResultState<UserModel?>>()
    val userModel: LiveData<ResultState<UserModel?>> = _userModel

    private val _topicsModel = MutableLiveData<ResultState<List<TopicModel>>>()
    val topicsModel: LiveData<ResultState<List<TopicModel>>> = _topicsModel

    init {
        viewModelScope.launch {
            getCredentialUseCase.execute(None).collectLatest { _userModel.value = it }
            getTopicUseCase.execute(None).collectLatest { _topicsModel.value = it }
        }
    }
}