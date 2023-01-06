package com.andriawan.askme.ui.screens.login.presenter

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.platform.LocalContext
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
import com.andriawan.askme.navigation.Routes
import com.andriawan.askme.ui.components.CustomButton
import com.andriawan.askme.ui.components.CustomSpannableLinkText
import com.andriawan.askme.ui.components.CustomTextInput
import com.andriawan.askme.ui.screens.login.models.LoginUiEvent
import com.andriawan.askme.ui.screens.login.viewmodel.LoginViewModel
import com.andriawan.askme.ui.themes.AskMeTheme
import com.andriawan.askme.ui.themes.HintTextInputColor
import com.andriawan.askme.utils.Constants.MINUS_ONE
import com.andriawan.askme.utils.extensions.orDefault
import com.andriawan.askme.utils.network.getError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    navController: NavController,
    snackBarHostState: SnackbarHostState,
    scope: CoroutineScope = rememberCoroutineScope()
) {
    val state = viewModel.loginUiState
    val context = LocalContext.current

    LaunchedEffect(state.showError) {
        scope.launch {
            if (state.showError != null) {
                snackBarHostState.showSnackbar(
                    message = state.showError.getError(context),
                    duration = SnackbarDuration.Short
                )
            }
        }
    }

    LoginContent(
        email = state.email,
        password = state.password,
        isPasswordVisible = state.isPasswordVisible,
        onEmailChanged = { viewModel.onEvent(LoginUiEvent.OnEmailChanged(it)) },
        onPasswordChanged = { viewModel.onEvent(LoginUiEvent.OnPasswordChanged(it)) },
        emailError = if (state.emailError != MINUS_ONE) state.emailError else null,
        passwordError = if (state.passwordError != MINUS_ONE) state.passwordError else null,
        onPasswordVisibilityChanged = {
            viewModel.onEvent(LoginUiEvent.OnPasswordVisibilityChanged(it))
        },
        onSubmitClicked = {
            viewModel.onEvent(LoginUiEvent.OnSubmitClicked)
        },
        onRegisterClicked = {
            navController.navigate(Routes.REGISTER_PAGE)
        },
        signInButtonEnabled = state.signInButtonEnabled
    )
}

@OptIn(ExperimentalComposeUiApi::class)
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
    onSubmitClicked: () -> Unit,
    onRegisterClicked: () -> Unit,
    signInButtonEnabled: Boolean
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
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .onPreviewKeyEvent {
                        if (it.key == Key.Tab) {
                            focusManager.moveFocus(FocusDirection.Down)
                            true
                        } else false
                    },
                label = stringResource(id = R.string.input_email_label_text),
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
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .onPreviewKeyEvent {
                        if (it.key == Key.Tab) {
                            focusManager.clearFocus()
                            true
                        } else false
                    },
                label = stringResource(id = R.string.input_password_label_text),
                hint = stringResource(id = R.string.input_password_hint_text),
                value = password,
                onValueChanged = onPasswordChanged,
                leadingIcon = painterResource(id = R.drawable.ic_password),
                trailingIcon = {
                    val iconType =
                        if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    val contentDescription =
                        if (isPasswordVisible) "Hide Password" else "Show Password"

                    IconButton(
                        onClick = {
                            onPasswordVisibilityChanged.invoke(!isPasswordVisible)
                        }
                    ) {
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
                style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Medium),
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(60.dp))
        }

        item {
            CustomButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                title = stringResource(id = R.string.sign_in_button_text),
                onButtonClicked = onSubmitClicked,
                enabled = signInButtonEnabled
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
                linkText = stringResource(id = R.string.sign_up_button_text),
                textAlign = TextAlign.Center,
                textStyle = MaterialTheme.typography.body2.copy(
                    fontWeight = FontWeight.Medium
                ),
                onLinkTextClicked = onRegisterClicked
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
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.body1
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun LoginScreenPreviewLight() {
    AskMeTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            LoginContent(
                email = "",
                password = "",
                onEmailChanged = { },
                onPasswordChanged = { },
                isPasswordVisible = false,
                onPasswordVisibilityChanged = { },
                onSubmitClicked = { },
                onRegisterClicked = { },
                signInButtonEnabled = false
            )
        }
    }
}
