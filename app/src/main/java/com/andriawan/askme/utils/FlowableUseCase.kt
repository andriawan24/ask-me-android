package com.andriawan.askme.utils

import kotlinx.coroutines.flow.Flow

interface FlowableUseCase<in PARAM, RESPONSE> {
    fun execute(param: PARAM): Flow<ResultState<RESPONSE>>
}