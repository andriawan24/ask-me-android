package com.andriawan.askme.ui.screens.splash.presenter

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
import com.andriawan.askme.navigation.Routes
import com.andriawan.askme.ui.screens.splash.viewmodel.SplashScreenViewModel
import com.andriawan.askme.ui.themes.AskMeTheme
import com.andriawan.askme.utils.Constants.EMPTY
import com.andriawan.askme.utils.None
import com.andriawan.askme.utils.SingleEvents

@Composable
fun SplashScreen(modifier: Modifier = Modifier) {
    SplashScreenContent(modifier = modifier)
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
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.onBackground
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun SplashScreenPreview() {
    AskMeTheme {
        Surface(color = MaterialTheme.colors.background) {
            SplashScreenContent()
        }
    }
}
