package com.andriawan.askme.ui.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColors = darkColors(
    primary = PrimaryColorDark,
    onPrimary = OnPrimaryColorDark,
    secondary = SecondaryColorDark,
    onSecondary = OnSecondaryColorDark,
    background = BackgroundColorDark,
    onBackground = OnBackgroundColorDark,
    error = ErrorColorDark,
    onError = OnErrorColorDark
)

private val LightColors = lightColors(
    primary = PrimaryColorLight,
    onPrimary = OnPrimaryColorLight,
    secondary = SecondaryColorLight,
    onSecondary = OnSecondaryColorLight,
    background = BackgroundColorLight,
    onBackground = OnBackgroundColorLight,
    error = ErrorColorLight,
    onError = OnErrorColorLight,
)

@Composable
fun AskMeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) DarkColors else LightColors,
        typography = AskMeTypography,
        shapes = AskMeShapes,
        content = content
    )
}