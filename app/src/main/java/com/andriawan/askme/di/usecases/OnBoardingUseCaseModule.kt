package com.andriawan.askme.di.usecases

import com.andriawan.askme.domain.repository.OnBoardingRepository
import com.andriawan.askme.domain.usecases.onboarding.GetFirstTimeUseCase
import com.andriawan.askme.domain.usecases.onboarding.SetFirstTimeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object OnBoardingUseCaseModule {

    @Provides
    @ViewModelScoped
    fun providesGetFirstTimeUseCase(
        repository: OnBoardingRepository
    ): GetFirstTimeUseCase = GetFirstTimeUseCase(repository)

    @Provides
    @ViewModelScoped
    fun providesSetFirstTimeUseCase(
        repository: OnBoardingRepository
    ): SetFirstTimeUseCase = SetFirstTimeUseCase(repository)
}