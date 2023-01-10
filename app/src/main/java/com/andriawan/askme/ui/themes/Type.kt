package com.andriawan.askme.ui.themes

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.andriawan.askme.R

val PoppinsFontFamily = FontFamily(
    Font(R.font.poppins),
    Font(R.font.poppins_extralight, weight = FontWeight.ExtraLight),
    Font(R.font.poppins_thin, weight = FontWeight.Thin),
    Font(R.font.poppins_medium, weight = FontWeight.Medium),
    Font(R.font.poppins_semibold, weight = FontWeight.SemiBold),
    Font(R.font.poppins_bold, weight = FontWeight.Bold)
)

val AskMeTypography = Typography(
    h1 = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        fontFamily = PoppinsFontFamily
    ),
    h2 = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 26.sp,
        fontFamily = PoppinsFontFamily
    ),
    h3 = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        fontFamily = PoppinsFontFamily
    ),
    h4 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp,
        fontFamily = PoppinsFontFamily
    ),
    h5 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        fontFamily = PoppinsFontFamily
    ),
    h6 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        fontFamily = PoppinsFontFamily
    ),
    body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        fontFamily = PoppinsFontFamily
    ),
    body2 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        fontFamily = PoppinsFontFamily
    ),
    caption = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Color.Gray,
        fontFamily = PoppinsFontFamily
    )
)