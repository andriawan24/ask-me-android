package com.andriawan.askme.domain.usecases

import com.andriawan.askme.domain.repository.OnBoardingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetFirstTimeUseCase(
    private val repository: OnBoardingRepository
) {

    fun getFirstTime(): Flow<Boolean> = flow {
        emit(repository.getFirstTime())
    }

    companion object {
        var INSTANCE: GetFirstTimeUseCase? = null
        fun getInstances(repository: OnBoardingRepository): GetFirstTimeUseCase {
            return INSTANCE ?: synchronized(this) {
                val newInstance = GetFirstTimeUseCase(repository).also {
                    INSTANCE = it
                }
                newInstance
            }
        }
    }
}