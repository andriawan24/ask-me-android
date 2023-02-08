package com.andriawan.askme.navigation

import android.content.Context
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.andriawan.askme.ui.screens.home.presenter.HomeScreen
import com.andriawan.askme.ui.screens.home.viewmodel.HomeViewModel
import com.andriawan.askme.ui.screens.login.models.LoginUiEvent
import com.andriawan.askme.ui.screens.login.presenter.LoginScreen
import com.andriawan.askme.ui.screens.login.viewmodel.LoginViewModel
import com.andriawan.askme.ui.screens.onboarding.models.OnBoardingUiEvent
import com.andriawan.askme.ui.screens.onboarding.presenter.OnBoardingScreen
import com.andriawan.askme.ui.screens.onboarding.viewmodel.OnBoardingViewModel
import com.andriawan.askme.ui.screens.questions.presenter.QuestionScreen
import com.andriawan.askme.ui.screens.questions.viewmodel.QuestionViewModel
import com.andriawan.askme.ui.screens.register.presenter.RegisterScreen
import com.andriawan.askme.ui.screens.register.viewmodel.RegisterViewModel
import com.andriawan.askme.ui.screens.splash.presenter.SplashScreen
import com.andriawan.askme.ui.screens.splash.viewmodel.SplashScreenViewModel
import com.andriawan.askme.utils.extensions.handleNavigation
import com.andriawan.askme.utils.extensions.handleNavigationWithSingleEvent
import com.andriawan.askme.utils.network.getError
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun AskMeNavigation(
    navController: NavHostController,
    startDestination: String = Routes.SPLASH_SCREEN_PAGE,
    snackBarHostState: SnackbarHostState,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    context: Context = LocalContext.current
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Routes.SPLASH_SCREEN_PAGE) {
            val viewModel: SplashScreenViewModel = hiltViewModel()
            LaunchedEffect(true) {
                lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    launch {
                        viewModel.navigate.collectLatest {
                            navController.handleNavigation(
                                destination = it,
                                popUpToRoute = Routes.SPLASH_SCREEN_PAGE,
                                inclusive = true
                            )
                        }
                    }
                }
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
            LaunchedEffect(true) {
                lifecycleOwner.lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                    launch {
                        viewModel.loginStatusChanged.collectLatest {
                            navController.navigate(Routes.HOME_PAGE)
                        }
                    }
                    launch {
                        viewModel.showErrorMessage.collectLatest {
                            snackBarHostState.showSnackbar(
                                message = it.getError(context),
                                duration = SnackbarDuration.Short
                            )
                        }
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
                navController = navController,
                snackBarHostState = snackBarHostState
            )
        }
        composable(Routes.HOME_PAGE) {
            val viewModel: HomeViewModel = hiltViewModel()
            LaunchedEffect(true) {
                lifecycleOwner.lifecycle.repeatOnLifecycle(state = Lifecycle.State.CREATED) {
                    launch {
                        viewModel.showErrorMessage.collectLatest {
                            snackBarHostState.showSnackbar(
                                message = it.getError(context),
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
                    launch {
                        viewModel.navigateToLogin.collectLatest {
                            navController.navigate(
                                route = Routes.LOGIN_PAGE,
                                navOptions = navOptions {
                                    popUpTo(Routes.LOGIN_PAGE) {
                                        inclusive = true
                                    }
                                }
                            )
                        }
                    }
                }
            }
            HomeScreen(state = viewModel.uiState)
        }
        composable(Routes.QUESTION_PAGE) {
            val viewModel = hiltViewModel<QuestionViewModel>()

            LaunchedEffect(true) {
                lifecycleOwner.lifecycle.repeatOnLifecycle(state = Lifecycle.State.CREATED) {
                    launch {
                        viewModel.initData()
                    }
                    launch {
                        viewModel.message.collectLatest {
                            snackBarHostState.showSnackbar(message = it.getError(context))
                        }
                    }
                }
            }

            QuestionScreen(
                uiState = viewModel.uiState,
                onQueryChanged = viewModel::updateQuery,
                onSearchSubmitted = viewModel::initData
            )
        }
    }
}