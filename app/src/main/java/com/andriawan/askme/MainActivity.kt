package com.andriawan.askme

import com.andriawan.askme.base.BaseActivity
import com.andriawan.askme.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        binding.navHostFragmentContainer
    }
}