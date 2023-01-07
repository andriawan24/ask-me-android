package com.andriawan.askme.domain.usecases.auth

import com.andriawan.askme.utils.*
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class SignOutUseCase @Inject constructor(
    private val sharedPreferencesHelper: SharedPreferencesHelper
): FlowableUseCase<None, None> {

    override fun execute(param: None): Flow<ResultState<None>> = flow {
        emit(ResultState.Loading())
        sharedPreferencesHelper.clearPreference(Constants.ACCESS_TOKEN_KEY)
        emit(ResultState.Success(None))
    }
}