package com.andriawan.askme.ui.screens.onboarding.presenter

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.andriawan.askme.R
import com.andriawan.askme.navigation.AskMeDestinations
import com.andriawan.askme.ui.components.CustomButton
import com.andriawan.askme.ui.screens.onboarding.models.OnBoardingUiEvent
import com.andriawan.askme.ui.screens.onboarding.viewmodel.OnBoardingViewModel
import com.andriawan.askme.ui.themes.AskMeTheme

@Composable
fun OnBoardingScreen(
    viewModel: OnBoardingViewModel,
    viewLifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    navController: NavController
) {
    viewModel.goToLoginPage.observe(viewLifecycleOwner) {
        it.getContentIfNotHandled()?.let {
            navController.navigate(AskMeDestinations.LOGIN_PAGE) {
                launchSingleTop = true
                popUpTo(AskMeDestinations.SPLASH_SCREEN_PAGE) {
                    inclusive = true
                }
            }
        }
    }

    OnBoardingContent(
        onGetStartedClicked = {
            viewModel.onEvent(OnBoardingUiEvent.GetStartedClicked)
        }
    )
}

@Composable
fun OnBoardingContent(onGetStartedClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_logo),
            contentDescription = stringResource(
                id = R.string.content_description_image_logo
            ),
            modifier = Modifier.size(70.dp)
        )
        Spacer(modifier = Modifier.height(36.dp))
        Text(
            text = stringResource(id = R.string.on_boarding_title_text),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(id = R.string.on_boarding_description_text),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(90.dp))
        CustomButton(
            onButtonClicked = onGetStartedClicked,
            title = stringResource(id = R.string.get_started_button_text),
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp)
        )
    }
}

@Preview
@Composable
fun OnBoardingScreenPreview() {
    AskMeTheme {
        Surface(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(24.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            OnBoardingContent(onGetStartedClicked = { })
        }
    }
}