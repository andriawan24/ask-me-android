package com.andriawan.askme.features.onboarding.presenter

import androidx.fragment.app.viewModels
import com.andriawan.askme.base.BaseFragment
import com.andriawan.askme.databinding.FragmentOnboardingBinding
import com.andriawan.askme.features.onboarding.viewmodel.OnBoardingViewModel

class OnBoardingFragment: BaseFragment<FragmentOnboardingBinding, OnBoardingViewModel>() {

    override val viewModel: OnBoardingViewModel by viewModels()
    override val binding: FragmentOnboardingBinding by lazy {
        FragmentOnboardingBinding.inflate(layoutInflater)
    }

    override fun initViews() {

    }
}