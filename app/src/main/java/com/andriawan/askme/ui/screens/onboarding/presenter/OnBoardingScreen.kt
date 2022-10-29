package com.andriawan.askme.ui.screens.onboarding.presenter

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import com.andriawan.askme.R
import com.andriawan.askme.ui.screens.onboarding.models.OnBoardingUiEvent
import com.andriawan.askme.ui.screens.onboarding.viewmodel.OnBoardingViewModel
import com.andriawan.askme.ui.themes.AskMeTheme
import com.andriawan.askme.ui.themes.OnPrimaryColorLight
import timber.log.Timber

@Composable
fun OnBoardingScreen(
    viewModel: OnBoardingViewModel,
    viewLifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    viewModel.goToLoginPage.observe(viewLifecycleOwner) {
        it.getContentIfNotHandled()?.let {
            Timber.d("Navigate to login page")
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
        Button(
            onClick = onGetStartedClicked,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            shape = MaterialTheme.shapes.small,
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 2.dp
            )
        ) {
            Text(
                text = stringResource(id = R.string.get_started_button_text),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                color = OnPrimaryColorLight
            )
        }
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