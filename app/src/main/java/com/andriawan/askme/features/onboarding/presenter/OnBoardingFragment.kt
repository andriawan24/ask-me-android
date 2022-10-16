package com.andriawan.askme.features.onboarding.presenter

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.andriawan.askme.base.BaseFragment
import com.andriawan.askme.databinding.FragmentOnboardingBinding
import com.andriawan.askme.features.onboarding.viewmodel.OnBoardingViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@AndroidEntryPoint
@FragmentScoped
class OnBoardingFragment : BaseFragment<FragmentOnboardingBinding, OnBoardingViewModel>() {

    override val viewModel: OnBoardingViewModel by viewModels()
    override val binding: FragmentOnboardingBinding by lazy {
        FragmentOnboardingBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        binding.getStartedButton.setOnClickListener {
            viewModel.setFirstTime()
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