package com.andriawan.askme.navigation

import android.content.Context
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.andriawan.askme.ui.screens.login.models.LoginUiEvent
import com.andriawan.askme.ui.screens.login.presenter.LoginScreen
import com.andriawan.askme.ui.screens.login.viewmodel.LoginViewModel
import com.andriawan.askme.ui.screens.onboarding.models.OnBoardingUiEvent
import com.andriawan.askme.ui.screens.onboarding.presenter.OnBoardingScreen
import com.andriawan.askme.ui.screens.onboarding.viewmodel.OnBoardingViewModel
import com.andriawan.askme.ui.screens.register.presenter.RegisterScreen
import com.andriawan.askme.ui.screens.register.viewmodel.RegisterViewModel
import com.andriawan.askme.ui.screens.splash.presenter.SplashScreen
import com.andriawan.askme.ui.screens.splash.viewmodel.SplashScreenViewModel
import com.andriawan.askme.utils.extensions.handleNavigationWithSingleEvent
import com.andriawan.askme.utils.network.getError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun AskMeNavigation(
    navController: NavHostController,
    startDestination: String = Routes.SPLASH_SCREEN_PAGE,
    snackBarHostState: SnackbarHostState,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    scope: CoroutineScope = rememberCoroutineScope(),
    context: Context = LocalContext.current
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Routes.SPLASH_SCREEN_PAGE) {
            val viewModel: SplashScreenViewModel = hiltViewModel()
            viewModel.navigateLoginPage.observe(lifecycleOwner) {
                navController.handleNavigationWithSingleEvent(
                    event = it,
                    destination = Routes.LOGIN_PAGE,
                    popUpToRoute = Routes.SPLASH_SCREEN_PAGE,
                    inclusive = true
                )
            }
            viewModel.navigateOnBoarding.observe(lifecycleOwner) {
                navController.handleNavigationWithSingleEvent(
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
            viewModel.goToLoginPage.observe(lifecycleOwner) {
                navController.handleNavigationWithSingleEvent(
                    event = it,
                    destination = Routes.LOGIN_PAGE,
                    popUpToRoute = Routes.SPLASH_SCREEN_PAGE,
                    inclusive = true
                )
            }
            OnBoardingScreen(
                onGetStartedClicked = {
                    viewModel.onEvent(OnBoardingUiEvent.GetStartedClicked)
                }
            )
        }
        composable(Routes.LOGIN_PAGE) {
            val viewModel: LoginViewModel = hiltViewModel()
            val state = viewModel.uiState
            LaunchedEffect(state.errorLogin) {
                scope.launch {
                    if (state.errorLogin != null) {
                        snackBarHostState.showSnackbar(
                            message = state.errorLogin.getError(context),
                            duration = SnackbarDuration.Short
                        )
                    }
                }
            }
            LoginScreen(
                email = state.email,
                password = state.password,
                isPasswordVisible = state.isPasswordVisible,
                onEmailChanged = { email ->
                    viewModel.onEvent(LoginUiEvent.OnEmailChanged(email))
                },
                onPasswordChanged = { password ->
                    viewModel.onEvent(LoginUiEvent.OnPasswordChanged(password))
                },
                onPasswordVisibilityChanged = { isVisible ->
                    viewModel.onEvent(LoginUiEvent.OnPasswordVisibilityChanged(isVisible))
                },
                onSubmitClicked = {
                    viewModel.onEvent(LoginUiEvent.OnSubmitClicked)
                },
                onRegisterButtonClicked = {
                    navController.navigate(Routes.REGISTER_PAGE)
                },
                emailError = state.emailError,
                passwordError = state.passwordError,
                signInButtonEnabled = state.signInButtonEnabled
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