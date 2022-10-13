package com.andriawan.askme.features.splash.presenter

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.andriawan.askme.base.BaseFragment
import com.andriawan.askme.data.local.datastore.AskMeDataStore
import com.andriawan.askme.databinding.FragmentSplashScreenBinding
import com.andriawan.askme.features.splash.viewmodel.SplashScreenViewModel

class CustomSplashFragment : BaseFragment<FragmentSplashScreenBinding, SplashScreenViewModel>() {

    override val viewModel: SplashScreenViewModel by viewModels()
    override val binding: FragmentSplashScreenBinding by lazy {
        FragmentSplashScreenBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        viewModel.initData(AskMeDataStore(requireContext()))
    }

    override fun initObservers() {
        viewModel.navigateOnBoarding.observe(this) {
            findNavController().navigate(
                CustomSplashFragmentDirections
                    .actionCustomSplashFragmentToOnBoardingFragment()
            )
        }

        viewModel.navigateLoginPage.observe(this) {
            it.getContentIfNotHandled()?.let {
                findNavController().navigate(
                    CustomSplashFragmentDirections
                        .actionCustomSplashFragmentToLoginFragment()
                )
            }
        }
    }
}