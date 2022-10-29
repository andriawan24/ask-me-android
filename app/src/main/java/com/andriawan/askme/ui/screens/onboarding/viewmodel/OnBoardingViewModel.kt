package com.andriawan.askme.ui.screens.onboarding.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andriawan.askme.domain.usecases.onboarding.SetFirstTimeUseCase
import com.andriawan.askme.ui.screens.onboarding.models.OnBoardingUiEvent
import com.andriawan.askme.utils.None
import com.andriawan.askme.utils.ResultState
import com.andriawan.askme.utils.SingleEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val setFirstTimeUseCase: SetFirstTimeUseCase
): ViewModel() {

    private var _goToLoginPage = MutableLiveData<SingleEvents<None>>()
    val goToLoginPage: LiveData<SingleEvents<None>> = _goToLoginPage

    fun setFirstTimeToFalse() {
        viewModelScope.launch {
            setFirstTimeUseCase.execute(SetFirstTimeUseCase.Param(true)).collectLatest {
                if (it is ResultState.Success) {
                    _goToLoginPage.value = SingleEvents(None)
                } else {
                    _goToLoginPage.value = SingleEvents(None)
                }
            }
        }
    }

    fun onEvent(event: OnBoardingUiEvent) {
        when (event) {
            OnBoardingUiEvent.GetStartedClicked -> setFirstTimeToFalse()
        }
    }
}