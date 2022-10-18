package com.andriawan.askme

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.andriawan.askme.base.BaseActivity
import com.andriawan.askme.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        val navHostFragment =
            (supportFragmentManager.findFragmentById(R.id.navHost_fragmentContainer) as NavHostFragment)
        val navController = navHostFragment.navController

        with(navController) {
            binding.bottomNavigation.apply {
                setupWithNavController(this@with)
                setOnItemSelectedListener {
                    when (it.itemId) {
                        R.id.homeFragment -> {
                            navigate(MainNavigationDirections.actionGlobalNavGraphHome())
                            return@setOnItemSelectedListener true
                        }
                        R.id.profileFragment -> {
                            navigate(MainNavigationDirections.actionGlobalNavGraphProfile())
                            return@setOnItemSelectedListener true
                        }

                        else -> {
                            return@setOnItemSelectedListener false
                        }
                    }
                }
            }

            addOnDestinationChangedListener { _, destination, _ ->
                binding.bottomNavigation.isVisible = mainVisibilityCheck(destination)
            }
        }
    }

    private fun mainVisibilityCheck(destination: NavDestination): Boolean {
        return destination.id == R.id.homeFragment ||
                destination.id == R.id.profileFragment
    }
}