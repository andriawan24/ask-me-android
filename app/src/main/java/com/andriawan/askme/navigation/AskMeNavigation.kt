package com.andriawan.askme.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.andriawan.askme.ui.screens.onboarding.presenter.OnBoardingScreen
import com.andriawan.askme.ui.screens.onboarding.viewmodel.OnBoardingViewModel
import com.andriawan.askme.ui.screens.splash.presenter.SplashScreen
import com.andriawan.askme.ui.screens.splash.viewmodel.SplashScreenViewModel

@Composable
fun AskMeNavigation(
    navController: NavHostController,
    startDestination: String = AskMeDestinations.SPLASH_SCREEN_PAGE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(AskMeDestinations.SPLASH_SCREEN_PAGE) {
            val viewModel: SplashScreenViewModel = hiltViewModel()
            SplashScreen(viewModel = viewModel, navController = navController)
        }
        composable(AskMeDestinations.ON_BOARDING_PAGE) {
            val viewModel: OnBoardingViewModel = hiltViewModel()
            OnBoardingScreen(viewModel = viewModel)
        }
    }
}

object AskMeDestinations {
    const val SPLASH_SCREEN_PAGE = "splash_screen"
    const val ON_BOARDING_PAGE = "on_boarding_page"
    const val LOGIN_PAGE = "login_page"
    const val REGISTER_PAGE = "register_page"
    const val HOME_PAGE = "home_page"
}