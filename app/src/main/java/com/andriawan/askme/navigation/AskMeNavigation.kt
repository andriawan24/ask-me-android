package com.andriawan.askme.navigation

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.andriawan.askme.ui.screens.login.presenter.LoginScreen
import com.andriawan.askme.ui.screens.login.viewmodel.LoginViewModel
import com.andriawan.askme.ui.screens.onboarding.presenter.OnBoardingScreen
import com.andriawan.askme.ui.screens.onboarding.viewmodel.OnBoardingViewModel
import com.andriawan.askme.ui.screens.register.presenter.RegisterScreen
import com.andriawan.askme.ui.screens.register.viewmodel.RegisterViewModel
import com.andriawan.askme.ui.screens.splash.presenter.SplashScreen
import com.andriawan.askme.ui.screens.splash.viewmodel.SplashScreenViewModel
import com.andriawan.askme.utils.extensions.handleNavigation

@Composable
fun AskMeNavigation(
    navController: NavHostController,
    startDestination: String = Routes.SPLASH_SCREEN_PAGE,
    snackBarHostState: SnackbarHostState
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Routes.SPLASH_SCREEN_PAGE) {
            val viewModel: SplashScreenViewModel = hiltViewModel()
            val lifecycleOwner = LocalLifecycleOwner.current
            viewModel.navigateLoginPage.observe(lifecycleOwner) {
                navController.handleNavigation(
                    event = it,
                    destination = Routes.LOGIN_PAGE,
                    popUpToRoute = Routes.SPLASH_SCREEN_PAGE,
                    inclusive = true
                )
            }
            viewModel.navigateOnBoarding.observe(lifecycleOwner) {
                navController.handleNavigation(
                    event = it,
                    destination = Routes.ON_BOARDING_PAGE,
                    popUpToRoute = Routes.SPLASH_SCREEN_PAGE,
                    inclusive = true
                )
            }
            SplashScreen()
        }
        composable(Routes.ON_BOARDING_PAGE) {
            val viewModel: OnBoardingViewModel = hiltViewModel()
            OnBoardingScreen(viewModel = viewModel, navController = navController)
        }
        composable(Routes.LOGIN_PAGE) {
            val viewModel: LoginViewModel = hiltViewModel()
            LoginScreen(
                viewModel = viewModel,
                navController = navController,
                snackBarHostState = snackBarHostState
            )
        }
        composable(Routes.REGISTER_PAGE) {
            val viewModel: RegisterViewModel = hiltViewModel()
            RegisterScreen(
                viewModel = viewModel,
                navController = navController
            )
        }
    }
}