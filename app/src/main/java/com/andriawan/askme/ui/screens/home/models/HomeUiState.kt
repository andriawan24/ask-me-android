package com.andriawan.askme.ui.screens.home.models

import com.andriawan.askme.domain.models.TopicModel
import com.andriawan.askme.domain.models.UserModel

data class HomeUiState(
    val topics: List<TopicModel>? = null,
    val user: UserModel? = null,
    val loadingUserModel: Boolean = false,
    val loadingTopics: Boolean = false
)