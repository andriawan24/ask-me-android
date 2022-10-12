package com.andriawan.askme.domain.repository

interface OnBoardingRepository {
    suspend fun getFirstTime(): Boolean
}