package com.andriawan.askme.data.repository

import com.andriawan.askme.data.local.datastore.AskMeDataStore
import com.andriawan.askme.domain.repository.OnBoardingRepository
import kotlinx.coroutines.flow.Flow

class OnBoardingRepositoryImpl(
    private val dataStore: AskMeDataStore
): OnBoardingRepository {

    override suspend fun getFirstTime(): Boolean {
        return dataStore.getFirstTime()
    }

    companion object {
        @Volatile var INSTANCE: OnBoardingRepository? = null
        fun getInstance(askMeDataStore: AskMeDataStore): OnBoardingRepository {
            return INSTANCE ?: synchronized(this) {
                val newInstance = OnBoardingRepositoryImpl(askMeDataStore).also {
                    INSTANCE = it
                }
                newInstance
            }
        }
    }
}