package com.andriawan.askme.features.splash.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andriawan.askme.data.local.datastore.AskMeDataStore
import com.andriawan.askme.utils.None
import com.andriawan.askme.utils.SingleEvents
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenViewModel: ViewModel() {

    private val _navigateOnBoarding = MutableLiveData<SingleEvents<None>>()
    val navigateOnBoarding: LiveData<SingleEvents<None>> = _navigateOnBoarding

    private val _navigateLoginPage = MutableLiveData<SingleEvents<None>>()
    val navigateLoginPage: LiveData<SingleEvents<None>> = _navigateLoginPage

    fun initData(askMeDataStore: AskMeDataStore) {
        viewModelScope.launch {
            delay(DEFAULT_SPLASH_SCREEN_DELAY)
            if (askMeDataStore.getFirstTime()) {
                _navigateOnBoarding.value = SingleEvents(None)
            } else {
                _navigateLoginPage.value = SingleEvents(None)
            }
        }
    }

    companion object {
        private const val DEFAULT_SPLASH_SCREEN_DELAY = 2000L
    }
}