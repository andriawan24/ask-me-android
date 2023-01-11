package com.andriawan.askme.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andriawan.askme.R
import com.andriawan.askme.ui.themes.AskMeTheme
import com.andriawan.askme.ui.themes.HintTextInputColor
import com.andriawan.askme.ui.themes.OnBackgroundColorLight
import com.andriawan.askme.utils.Constants.MINUS_ONE

@Composable
fun CustomTextInput(
    modifier: Modifier = Modifier,
    label: String? = null,
    labelColor: Color = MaterialTheme.colors.onBackground,
    hint: String,
    value: String,
    onValueChanged: (String) -> Unit,
    leadingIcon: Painter? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    trailingIcon: @Composable () -> Unit = {},
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isError: Boolean = false,
    @StringRes errorText: Int = MINUS_ONE,
    singleLine: Boolean = true
) {
    Column(modifier = modifier.fillMaxWidth()) {
        if (label != null) {
            Text(
                text = label,
                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.SemiBold),
                color = labelColor
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        if (leadingIcon != null) {
            TextField(
                value = value,
                onValueChange = onValueChanged,
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = hint,
                        style = MaterialTheme.typography.body1,
                        color = HintTextInputColor,
                        modifier = Modifier.wrapContentHeight(Alignment.CenterVertically)
                    )
                },
                leadingIcon = {
                    Icon(
                        painter = leadingIcon,
                        contentDescription = "$label icon",
                        modifier = Modifier.size(24.dp),
                        tint = HintTextInputColor
                    )
                },
                trailingIcon = trailingIcon,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Unspecified,
                    unfocusedIndicatorColor = Color.Unspecified
                ),
                shape = MaterialTheme.shapes.small,
                textStyle = MaterialTheme.typography.body1.copy(
                    color = OnBackgroundColorLight
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType,
                    imeAction = imeAction
                ),
                keyboardActions = keyboardActions,
                visualTransformation = visualTransformation,
                isError = isError,
                singleLine = singleLine
            )
        } else {
            TextField(
                value = value,
                onValueChange = onValueChanged,
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = hint,
                        style = MaterialTheme.typography.body1,
                        color = HintTextInputColor,
                        modifier = Modifier.wrapContentHeight(Alignment.CenterVertically)
                    )
                },
                trailingIcon = trailingIcon,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Unspecified,
                    unfocusedIndicatorColor = Color.Unspecified
                ),
                shape = MaterialTheme.shapes.small,
                textStyle = MaterialTheme.typography.body1.copy(
                    color = OnBackgroundColorLight
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType,
                    imeAction = imeAction
                ),
                keyboardActions = keyboardActions,
                visualTransformation = visualTransformation,
                isError = isError,
                singleLine = singleLine
            )
        }

        if (errorText != MINUS_ONE) {
            Text(
                text = stringResource(id = errorText),
                style = MaterialTheme.typography.body2.copy(
                    color = MaterialTheme.colors.onError
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun CustomTextInputPreviewLight() {
    AskMeTheme {
        Surface(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .padding(24.dp)
        ) {
            CustomTextInput(
                modifier = Modifier.background(MaterialTheme.colors.background),
                label = "Email",
                hint = "Enter your email",
                value = "",
                onValueChanged = { },
                leadingIcon = painterResource(id = R.drawable.ic_email)
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun CustomTextInputNoIconPreviewLight() {
    AskMeTheme {
        Surface(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .padding(24.dp)
        ) {
            CustomTextInput(
                modifier = Modifier.background(MaterialTheme.colors.background),
                label = "Email",
                hint = "Enter your email",
                value = "",
                onValueChanged = { }
            )
        }
    }
}