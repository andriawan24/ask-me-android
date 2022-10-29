package com.andriawan.askme.ui.features.splash.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andriawan.askme.domain.usecases.onboarding.GetFirstTimeUseCase
import com.andriawan.askme.utils.None
import com.andriawan.askme.utils.ResultState
import com.andriawan.askme.utils.SingleEvents
import com.andriawan.askme.utils.extensions.orFalse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val getFirstTimeUseCase: GetFirstTimeUseCase
): ViewModel() {

    private val _navigateOnBoarding = MutableLiveData<SingleEvents<None>>()
    val navigateOnBoarding: LiveData<SingleEvents<None>> = _navigateOnBoarding

    private val _navigateLoginPage = MutableLiveData<SingleEvents<None>>()
    val navigateLoginPage: LiveData<SingleEvents<None>> = _navigateLoginPage

    fun initData() {
        viewModelScope.launch {
            delay(DEFAULT_SPLASH_SCREEN_DELAY)
            when (val firstTimeResponse = getFirstTimeUseCase.execute(None).first()) {
                is ResultState.Success -> {
                    if (!firstTimeResponse.data.orFalse()) {
                        _navigateLoginPage.value = SingleEvents(None)
                    } else {
                        _navigateOnBoarding.value = SingleEvents(None)
                    }
                }

                else -> {
                    _navigateOnBoarding.value = SingleEvents(None)
                }
            }
        }
    }

    companion object {
        private const val DEFAULT_SPLASH_SCREEN_DELAY = 2000L
    }
}