package com.andriawan.askme.data.repository

import com.andriawan.askme.data.local.datastore.AskMeDataStore
import com.andriawan.askme.domain.repository.OnBoardingRepository
import javax.inject.Inject

class OnBoardingRepositoryImpl @Inject constructor(
    private val dataStore: AskMeDataStore
): OnBoardingRepository {

    override suspend fun getFirstTime(): Boolean {
        return dataStore.getFirstTime()
    }

    override suspend fun setFirstTime(isFirstTime: Boolean) {
        dataStore.setFirstTime(isFirstTime)
    }
}