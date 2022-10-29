package com.andriawan.askme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                Text(text = "Hello World")
            }
        }
    }

//    override fun initViews() {
//        val navHostFragment =
//            (supportFragmentManager.findFragmentById(R.id.navHost_fragmentContainer) as NavHostFragment)
//        val navController = navHostFragment.navController
//
//        with(navController) {
//            binding.bottomNavigation.apply {
//                setupWithNavController(this@with)
//                setOnItemSelectedListener {
//                    when (it.itemId) {
//                        R.id.homeFragment -> {
//                            navigate(MainNavigationDirections.actionGlobalNavGraphHome())
//                            return@setOnItemSelectedListener true
//                        }
//                        R.id.profileFragment -> {
//                            navigate(MainNavigationDirections.actionGlobalNavGraphProfile())
//                            return@setOnItemSelectedListener true
//                        }
//
//                        else -> {
//                            return@setOnItemSelectedListener false
//                        }
//                    }
//                }
//            }
//
//            addOnDestinationChangedListener { _, destination, _ ->
//                binding.bottomNavigation.isVisible = mainVisibilityCheck(destination)
//            }
//        }
//    }
//
//    private fun mainVisibilityCheck(destination: NavDestination): Boolean {
//        return destination.id == R.id.homeFragment ||
//                destination.id == R.id.profileFragment
//    }
}