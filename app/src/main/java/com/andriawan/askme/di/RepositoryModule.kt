package com.andriawan.askme.di

import com.andriawan.askme.data.repository.AuthRepositoryImpl
import com.andriawan.askme.data.repository.OnBoardingRepositoryImpl
import com.andriawan.askme.data.repository.QuestionRepositoryImpl
import com.andriawan.askme.data.repository.TopicRepositoryImpl
import com.andriawan.askme.domain.repository.AuthRepository
import com.andriawan.askme.domain.repository.OnBoardingRepository
import com.andriawan.askme.domain.repository.QuestionRepository
import com.andriawan.askme.domain.repository.TopicRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindsOnBoardingRepository(impl: OnBoardingRepositoryImpl): OnBoardingRepository

    @Binds
    @Singleton
    abstract fun bindsTopicRepository(impl: TopicRepositoryImpl): TopicRepository

    @Binds
    @Singleton
    abstract fun bindsQuestionRepository(impl: QuestionRepositoryImpl): QuestionRepository
}