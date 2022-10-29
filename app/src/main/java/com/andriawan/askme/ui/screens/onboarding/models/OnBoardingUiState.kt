package com.andriawan.askme.ui.screens.onboarding.models

sealed class OnBoardingUiEvent {
    object GetStartedClicked : OnBoardingUiEvent()
}