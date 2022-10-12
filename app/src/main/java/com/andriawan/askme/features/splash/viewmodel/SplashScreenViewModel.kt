package com.andriawan.askme.features.splash.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andriawan.askme.utils.None
import com.andriawan.askme.utils.SingleEvents
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenViewModel: ViewModel() {

    private val _navigateOnBoarding = MutableLiveData<SingleEvents<None>>()
    val navigateOnBoarding: LiveData<SingleEvents<None>> = _navigateOnBoarding

    init {
        viewModelScope.launch {
            delay(DEFAULT_SPLASH_SCREEN_DELAY)
            _navigateOnBoarding.value = SingleEvents(None)
        }
    }

    companion object {
        private const val DEFAULT_SPLASH_SCREEN_DELAY = 2000L
    }
}