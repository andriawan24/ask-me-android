package com.andriawan.askme.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andriawan.askme.ui.themes.AskMeTheme
import com.andriawan.askme.ui.themes.OnPrimaryColorLight

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    title: String,
    shape: Shape = MaterialTheme.shapes.small,
    elevation: ButtonElevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp),
    onButtonClicked: () -> Unit
) {
    Button(
        onClick = onButtonClicked,
        modifier = modifier,
        shape = shape,
        elevation = elevation
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.SemiBold
            ),
            color = OnPrimaryColorLight,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(top = 2.dp)
        )
    }
}

@Preview
@Composable
fun CustomButtonPreviewLight() {
    AskMeTheme {
        CustomButton(
            onButtonClicked = { },
            title = stringResource(id = com.andriawan.askme.R.string.get_started_button_text)
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun CustomButtonPreviewDark() {
    AskMeTheme {
        CustomButton(
            onButtonClicked = { },
            title = stringResource(id = com.andriawan.askme.R.string.get_started_button_text)
        )
    }
}