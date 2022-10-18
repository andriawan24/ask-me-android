package com.andriawan.askme.features.profile.presenter

import androidx.fragment.app.viewModels
import com.andriawan.askme.base.BaseFragment
import com.andriawan.askme.databinding.FragmentProfileBinding
import com.andriawan.askme.features.profile.viewmodel.ProfileViewModel

class ProfileFragment: BaseFragment<FragmentProfileBinding, ProfileViewModel>() {

    override val viewModel: ProfileViewModel by viewModels()
    override val binding: FragmentProfileBinding by lazy {
        FragmentProfileBinding.inflate(layoutInflater)
    }

    override fun initViews() {

    }

    override fun initObservers() {

    }
}