package com.andriawan.askme.di

import com.andriawan.askme.data.repository.AuthRepositoryImpl
import com.andriawan.askme.data.repository.OnBoardingRepositoryImpl
import com.andriawan.askme.domain.repository.AuthRepository
import com.andriawan.askme.domain.repository.OnBoardingRepository
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
}