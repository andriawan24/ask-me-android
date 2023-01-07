package com.andriawan.askme.domain.usecases.onboarding

import com.andriawan.askme.domain.repository.OnBoardingRepository
import com.andriawan.askme.utils.FlowableUseCase
import com.andriawan.askme.utils.None
import com.andriawan.askme.utils.ResultState
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class GetFirstTimeUseCase @Inject constructor(
    private val repository: OnBoardingRepository
): FlowableUseCase<None, Boolean> {

    override fun execute(param: None): Flow<ResultState<Boolean>> = flow {
        emit(ResultState.Success(repository.getFirstTime()))
    }
}