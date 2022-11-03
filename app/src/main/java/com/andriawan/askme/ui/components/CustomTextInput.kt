package com.andriawan.askme.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
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
import com.andriawan.askme.utils.Constants.EMPTY
import com.andriawan.askme.utils.Constants.MINUS_ONE

@ExperimentalMaterial3Api
@Composable
fun CustomTextInput(
    modifier: Modifier = Modifier,
    label: String,
    labelColor: Color = MaterialTheme.colorScheme.onBackground,
    hint: String,
    value: String,
    onValueChanged: (String) -> Unit,
    leadingIcon: Painter,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    trailingIcon: @Composable () -> Unit = {},
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isError: Boolean = false,
    @StringRes errorText: Int = MINUS_ONE
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
            color = labelColor
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChanged,
            placeholder = {
                Text(
                    text = hint,
                    style = MaterialTheme.typography.bodyMedium,
                    color = HintTextInputColor,
                    modifier = Modifier
                        .wrapContentHeight(Alignment.CenterVertically)
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
                containerColor = Color.White,
                focusedIndicatorColor = Color.Unspecified,
                unfocusedIndicatorColor = Color.Unspecified
            ),
            shape = MaterialTheme.shapes.small,
            textStyle = MaterialTheme.typography.bodyMedium,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation,
            isError = isError,
            supportingText = {
                if (isError && errorText != MINUS_ONE) Text(text = stringResource(id = errorText))
            }
        )
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun CustomTextInputPreviewLight() {
    AskMeTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(24.dp)
        ) {
            CustomTextInput(
                label = "Email",
                hint = "Enter your email",
                value = "Name",
                onValueChanged = { },
                leadingIcon = painterResource(id = R.drawable.ic_email)
            )
        }
    }
}

@ExperimentalMaterial3Api
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun CustomTextInputPreviewDark() {
    AskMeTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(24.dp)
        ) {
            CustomTextInput(
                label = "Email",
                hint = "Enter your email",
                value = "",
                onValueChanged = { },
                leadingIcon = painterResource(id = R.drawable.ic_email)
            )
        }
    }
}