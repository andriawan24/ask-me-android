package com.andriawan.askme.di.usecases

import com.andriawan.askme.domain.repository.AuthRepository
import com.andriawan.askme.domain.usecases.auth.GetCredentialUseCase
import com.andriawan.askme.domain.usecases.auth.SignInUseCase
import com.andriawan.askme.domain.usecases.auth.SignOutUseCase
import com.andriawan.askme.domain.usecases.auth.SignUpUseCase
import com.andriawan.askme.utils.SharedPreferencesHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AuthUseCaseModule {

    @Provides
    @ViewModelScoped
    fun providesSignInUseCase(
        repository: AuthRepository,
        sharedPreferencesHelper: SharedPreferencesHelper
    ): SignInUseCase = SignInUseCase(repository, sharedPreferencesHelper)

    @Provides
    @ViewModelScoped
    fun providesSignUpUseCase(
        repository: AuthRepository
    ): SignUpUseCase = SignUpUseCase(repository)

    @Provides
    @ViewModelScoped
    fun providesGetCredentialUseCase(
        repository: AuthRepository
    ): GetCredentialUseCase = GetCredentialUseCase(repository)

    @Provides
    @ViewModelScoped
    fun providesSignOutUseCase(
        sharedPreferencesHelper: SharedPreferencesHelper
    ): SignOutUseCase = SignOutUseCase(sharedPreferencesHelper)
}