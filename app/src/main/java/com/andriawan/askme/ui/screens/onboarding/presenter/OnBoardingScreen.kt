package com.andriawan.askme.ui.screens.onboarding.presenter

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andriawan.askme.R
import com.andriawan.askme.ui.components.CustomButton
import com.andriawan.askme.ui.themes.AskMeTheme

@Composable
fun OnBoardingScreen(
    onGetStartedClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.img_logo),
            contentDescription = stringResource(R.string.content_description_image_logo),
            modifier = Modifier.size(70.dp)
        )
        Spacer(modifier = Modifier.height(36.dp))
        Text(
            text = stringResource(R.string.on_boarding_title_text),
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.onBackground
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.on_boarding_description_text),
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        Spacer(modifier = Modifier.height(90.dp))
        CustomButton(
            onButtonClicked = onGetStartedClicked,
            title = stringResource(R.string.get_started_button_text),
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp)
        )
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun OnBoardingScreenPreview() {
    AskMeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            OnBoardingScreen(
                onGetStartedClicked = { }
            )
        }
    }
}