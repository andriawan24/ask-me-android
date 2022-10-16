package com.andriawan.askme.domain.usecases.onboarding

import com.andriawan.askme.domain.repository.OnBoardingRepository
import com.andriawan.askme.utils.FlowableUseCase
import com.andriawan.askme.utils.None
import com.andriawan.askme.utils.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SetFirstTimeUseCase(
    private val repository: OnBoardingRepository
): FlowableUseCase<SetFirstTimeUseCase.Param, None> {

    override fun execute(param: Param): Flow<ResultState<None>> = flow {
        repository.setFirstTime(param.isFirstTime)
        emit(ResultState.Success(None))
    }

    data class Param(
        val isFirstTime: Boolean
    )
}