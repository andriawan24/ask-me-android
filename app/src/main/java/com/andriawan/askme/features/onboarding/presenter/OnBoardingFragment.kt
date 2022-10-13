package com.andriawan.askme.features.onboarding.presenter

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.andriawan.askme.base.BaseFragment
import com.andriawan.askme.data.local.datastore.AskMeDataStore
import com.andriawan.askme.data.repository.OnBoardingRepositoryImpl
import com.andriawan.askme.databinding.FragmentOnboardingBinding
import com.andriawan.askme.domain.usecases.GetFirstTimeUseCase
import com.andriawan.askme.features.onboarding.viewmodel.OnBoardingViewModel

class OnBoardingFragment : BaseFragment<FragmentOnboardingBinding, OnBoardingViewModel>() {

    override val viewModel: OnBoardingViewModel by viewModels()
    override val binding: FragmentOnboardingBinding by lazy {
        FragmentOnboardingBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        val dataStore = AskMeDataStore(requireContext())
        binding.getStartedButton.setOnClickListener {
            viewModel.setFirstTime(dataStore)
        }
    }

    override fun initObservers() {
        viewModel.goToLoginPage.observe(this) {
            it.getContentIfNotHandled()?.let {
                findNavController().navigate(
                    OnBoardingFragmentDirections
                        .actionOnBoardingFragmentToLoginFragment()
                )
            }
        }
    }
}