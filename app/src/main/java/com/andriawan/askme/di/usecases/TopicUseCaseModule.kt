package com.andriawan.askme.di.usecases

import com.andriawan.askme.domain.repository.TopicRepository
import com.andriawan.askme.domain.usecases.topic.GetTopicUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TopicUseCaseModule {

    @Provides
    @ViewModelScoped
    fun providesGetTopicsUseCase(
        repository: TopicRepository
    ): GetTopicUseCase = GetTopicUseCase(repository)
}