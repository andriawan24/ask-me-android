package com.andriawan.askme.features.onboarding.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andriawan.askme.data.local.datastore.AskMeDataStore
import com.andriawan.askme.utils.None
import com.andriawan.askme.utils.SingleEvents
import kotlinx.coroutines.launch

class OnBoardingViewModel: ViewModel() {

    private var _goToLoginPage = MutableLiveData<SingleEvents<None>>()
    val goToLoginPage: LiveData<SingleEvents<None>> = _goToLoginPage

    fun setFirstTime(dataStore: AskMeDataStore) {
        viewModelScope.launch {
            dataStore.setFirstTime(false)
            _goToLoginPage.value = SingleEvents(None)
        }
    }
}