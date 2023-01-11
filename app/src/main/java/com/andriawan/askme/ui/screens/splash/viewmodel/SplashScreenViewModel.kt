package com.andriawan.askme.ui.screens.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andriawan.askme.domain.usecases.auth.GetCredentialUseCase
import com.andriawan.askme.domain.usecases.onboarding.GetFirstTimeUseCase
import com.andriawan.askme.navigation.Routes
import com.andriawan.askme.utils.None
import com.andriawan.askme.utils.ResultState
import com.andriawan.askme.utils.extensions.orFalse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val getFirstTimeUseCase: GetFirstTimeUseCase,
    private val getCredentialUseCase: GetCredentialUseCase
) : ViewModel() {

    private val _navigate = MutableSharedFlow<String>()
    val navigate = _navigate.asSharedFlow()

    init {
        viewModelScope.launch {
            delay(DEFAULT_SPLASH_SCREEN_DELAY)
            when (val firstTimeResponse = getFirstTimeUseCase.execute(None).first()) {
                is ResultState.Success -> {
                    if (firstTimeResponse.data.orFalse()) {
                        _navigate.emit(Routes.ON_BOARDING_PAGE)
                    } else {
                        getCredentialUseCase.execute(None).collectLatest {
                            when (it) {
                                is ResultState.Success -> {
                                    _navigate.emit(Routes.HOME_PAGE)
                                }
                                is ResultState.Error -> {
                                    _navigate.emit(Routes.LOGIN_PAGE)
                                }
                                else -> {
                                    // No-ops
                                }
                            }
                        }
                    }
                }

                else -> {
                    _navigate.emit(Routes.ON_BOARDING_PAGE)
                }
            }
        }
    }

    companion object {
        private const val DEFAULT_SPLASH_SCREEN_DELAY = 2000L
    }
}