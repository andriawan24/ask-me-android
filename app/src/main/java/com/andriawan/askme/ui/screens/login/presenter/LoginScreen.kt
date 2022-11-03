package com.andriawan.askme.ui.screens.login.presenter

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.andriawan.askme.R
import com.andriawan.askme.ui.components.CustomButton
import com.andriawan.askme.ui.components.CustomSpannableLinkText
import com.andriawan.askme.ui.components.CustomTextInput
import com.andriawan.askme.ui.screens.login.models.LoginUiEvent
import com.andriawan.askme.ui.screens.login.viewmodel.LoginViewModel
import com.andriawan.askme.ui.themes.AskMeTheme
import com.andriawan.askme.ui.themes.HintTextInputColor
import com.andriawan.askme.utils.Constants.MINUS_ONE
import com.andriawan.askme.utils.extensions.orDefault
import timber.log.Timber

@ExperimentalMaterial3Api
@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    navController: NavController
) {
    val state = viewModel.loginUiState

    LoginContent(
        email = state.email,
        password = state.password,
        onEmailChanged = { viewModel.onEvent(LoginUiEvent.OnEmailChanged(it)) },
        onPasswordChanged = { viewModel.onEvent(LoginUiEvent.OnPasswordChanged(it)) },
        isPasswordVisible = state.isPasswordVisible,
        onPasswordVisibilityChanged = {
            viewModel.onEvent(LoginUiEvent.OnPasswordVisibilityChanged(it))
        },
        onSubmitClicked = { },
        emailError = if (state.emailError != MINUS_ONE) state.emailError else null,
        passwordError = if (state.passwordError != MINUS_ONE) state.passwordError else null
    )
}

@ExperimentalMaterial3Api
@Composable
fun LoginContent(
    email: String,
    password: String,
    @DrawableRes emailError: Int? = null,
    @DrawableRes passwordError: Int? = null,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    isPasswordVisible: Boolean,
    onPasswordVisibilityChanged: (Boolean) -> Unit,
    onSubmitClicked: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            LoginTitleText(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(top = 24.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
        }
        item {
            CustomTextInput(
                modifier = Modifier.padding(horizontal = 24.dp),
                label = stringResource(id = R.string.email_text),
                hint = stringResource(id = R.string.input_email_hint_text),
                value = email,
                onValueChanged = onEmailChanged,
                leadingIcon = painterResource(id = R.drawable.ic_email),
                keyboardType = KeyboardType.Email,
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                imeAction = ImeAction.Next,
                isError = emailError != null,
                errorText = emailError.orDefault(MINUS_ONE)
            )
            Spacer(modifier = Modifier.height(30.dp))
        }
        item {
            CustomTextInput(
                modifier = Modifier.padding(horizontal = 24.dp),
                label = stringResource(id = R.string.password_text),
                hint = stringResource(id = R.string.password_hint_text),
                value = password,
                onValueChanged = onPasswordChanged,
                leadingIcon = painterResource(id = R.drawable.ic_password),
                trailingIcon = {
                    val iconType =
                        if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    val contentDescription =
                        if (isPasswordVisible) "Hide Password" else "Show Password"

                    IconButton(onClick = { onPasswordVisibilityChanged.invoke(!isPasswordVisible) }) {
                        Icon(
                            iconType,
                            contentDescription = contentDescription,
                            tint = HintTextInputColor
                        )
                    }
                },
                imeAction = ImeAction.Done,
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus(true)
                    }
                ),
                keyboardType = KeyboardType.Password,
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                isError = passwordError != null,
                errorText = passwordError.orDefault(MINUS_ONE)
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            Text(
                text = stringResource(id = R.string.forgot_password_text),
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium),
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(60.dp))
        }
        item {
            CustomButton(
                title = stringResource(id = R.string.sign_in_button_text),
                onButtonClicked = onSubmitClicked,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
        }
        item {
            CustomSpannableLinkText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 24.dp),
                fullText = stringResource(id = R.string.do_not_have_account_text),
                linkText = stringResource(id = R.string.sign_up),
                textAlign = TextAlign.Center,
                textStyle = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Medium
                ),
                onLinkTextClicked = {
                    Timber.d("onClickedSignUp")
                }
            )
        }
    }
}

@Composable
private fun LoginTitleText(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        CustomSpannableLinkText(
            fullText = stringResource(id = R.string.login_welcome_title_text),
            linkText = stringResource(id = R.string.app_title)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.login_welcome_subtitle_text),
            color = MaterialTheme.colorScheme.tertiary,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun LoginScreenPreviewLight() {
    AskMeTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LoginContent(
                email = "",
                password = "",
                onEmailChanged = { },
                onPasswordChanged = { },
                isPasswordVisible = false,
                onPasswordVisibilityChanged = { },
                onSubmitClicked = { },
            )
        }
    }
}

@ExperimentalMaterial3Api
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun LoginScreenPreviewDark() {
    AskMeTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LoginContent(
                email = "email@gmail.com",
                password = "Password",
                onEmailChanged = { },
                onPasswordChanged = { },
                isPasswordVisible = true,
                onPasswordVisibilityChanged = { },
                onSubmitClicked = { },
            )
        }
    }
}