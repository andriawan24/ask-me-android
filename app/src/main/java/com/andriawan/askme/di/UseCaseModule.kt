package com.andriawan.askme.di

import com.andriawan.askme.domain.repository.AuthRepository
import com.andriawan.askme.domain.usecases.auth.SignInUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun providesSignInUseCase(repository: AuthRepository): SignInUseCase = SignInUseCase(repository)
}