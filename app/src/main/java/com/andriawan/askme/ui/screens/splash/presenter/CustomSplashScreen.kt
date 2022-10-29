package com.andriawan.askme.ui.screens.splash.presenter

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.andriawan.askme.R
import com.andriawan.askme.navigation.AskMeDestinations
import com.andriawan.askme.ui.screens.splash.viewmodel.SplashScreenViewModel
import com.andriawan.askme.ui.themes.AskMeTheme
import com.andriawan.askme.utils.None
import com.andriawan.askme.utils.SingleEvents

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel: SplashScreenViewModel,
    navController: NavController,
    viewLifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    viewModel.navigateLoginPage.observe(viewLifecycleOwner) {
        handleNavigationEvent(it, navController, AskMeDestinations.LOGIN_PAGE)
    }

    viewModel.navigateOnBoarding.observe(viewLifecycleOwner) {
        handleNavigationEvent(it, navController, AskMeDestinations.ON_BOARDING_PAGE)
    }

    SplashScreenContent(modifier = modifier)
}

private fun handleNavigationEvent(
    event: SingleEvents<None>,
    navController: NavController,
    page: String
) {
    event.getContentIfNotHandled()?.let {
        navController.navigate(page)
    }
}

@Composable
fun SplashScreenContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(40.dp, Alignment.CenterVertically)
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_logo),
            contentDescription = stringResource(R.string.content_description_image_logo),
            modifier = Modifier.size(150.dp)
        )
        Text(
            text = stringResource(id = R.string.app_title),
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    AskMeTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            SplashScreenContent()
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SplashScreenPreviewDarkMode() {
    AskMeTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            SplashScreenContent()
        }
    }
}