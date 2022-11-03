package com.andriawan.askme.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andriawan.askme.R
import com.andriawan.askme.ui.themes.AskMeTheme
import com.andriawan.askme.utils.Constants.NEW_LINE

@Composable
fun CustomSpannableLinkText(
    modifier: Modifier = Modifier,
    fullText: String,
    linkText: String,
    onLinkTextClicked: () -> Unit = {},
    textStyle: TextStyle = MaterialTheme.typography.headlineLarge,
    textAlign: TextAlign = TextAlign.Start
) {
    val spannableStringText = buildAnnotatedString {
        append(fullText)
        val startIndexLink = fullText.indexOf(linkText)
        val endIndexLink = startIndexLink + linkText.length
        addStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.primary
            ), start = startIndexLink, end = endIndexLink
        )
        addStringAnnotation(
            tag = linkText,
            start = startIndexLink,
            end = endIndexLink,
            annotation = linkText
        )
    }

    ClickableText(
        modifier = modifier,
        text = spannableStringText,
        onClick = {
            spannableStringText
                .getStringAnnotations(linkText, it, it)
                .firstOrNull()?.let {
                    onLinkTextClicked.invoke()
                }
        },
        style = textStyle.copy(
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = textAlign
        )
    )
}

@Preview
@Composable
fun CustomSpannableLinkTextPreviewLight() {
    AskMeTheme {
        Surface(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(24.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            CustomSpannableLinkText(
                fullText = stringResource(id = R.string.login_welcome_title_text),
                linkText = stringResource(id = R.string.app_title),
                onLinkTextClicked = { }
            )
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun CustomSpannableLinkTextDark() {
    AskMeTheme {
        Surface(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(24.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            CustomSpannableLinkText(
                fullText = stringResource(id = R.string.login_welcome_title_text) + NEW_LINE,
                linkText = stringResource(id = R.string.app_title),
                onLinkTextClicked = { }
            )
        }
    }
}