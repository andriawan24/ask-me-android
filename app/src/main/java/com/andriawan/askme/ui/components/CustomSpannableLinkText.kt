package com.andriawan.askme.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andriawan.askme.ui.themes.AskMeTheme

@Composable
fun CustomSpannableLinkText(
    modifier: Modifier = Modifier,
    fullText: String,
    linkText: String,
    onLinkTextClicked: () -> Unit = {},
    textStyle: TextStyle = MaterialTheme.typography.h2,
    textAlign: TextAlign = TextAlign.Start
) {
    val spannableStringText = buildAnnotatedString {
        append(fullText)
        val startIndexLink = fullText.indexOf(linkText)
        val endIndexLink = startIndexLink + linkText.length
        addStyle(
            style = SpanStyle(
                color = MaterialTheme.colors.primary
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
            color = MaterialTheme.colors.onBackground,
            textAlign = textAlign
        )
    )
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun CustomSpannableLinkTextPreviewLight() {
    AskMeTheme {
        Surface(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .padding(24.dp)
        ) {
            CustomSpannableLinkText(
                fullText = "Welcome back to ASK ME",
                linkText = "This is link",
                onLinkTextClicked = { }
            )
        }
    }
}