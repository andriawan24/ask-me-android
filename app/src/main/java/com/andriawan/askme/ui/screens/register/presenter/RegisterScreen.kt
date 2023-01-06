package com.andriawan.askme.ui.screens.register.presenter

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.andriawan.askme.R
import com.andriawan.askme.ui.components.CustomButton
import com.andriawan.askme.ui.components.CustomSpannableLinkText
import com.andriawan.askme.ui.components.CustomTextInput
import com.andriawan.askme.ui.components.LifecycleComposable
import com.andriawan.askme.ui.screens.register.models.RegisterUiEvent
import com.andriawan.askme.ui.screens.register.viewmodel.RegisterViewModel
import com.andriawan.askme.ui.themes.AskMeTheme
import com.andriawan.askme.ui.themes.HintTextInputColor
import com.andriawan.askme.utils.Constants
import com.andriawan.askme.utils.extensions.orDefault
import timber.log.Timber

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel,
    navController: NavController
) {
    val state = viewModel.state

    LifecycleComposable { owner, event ->
        if (event == Lifecycle.Event.ON_START) {
            viewModel.signUpResponse.observe(owner) {
                Timber.d("Result is $it")
            }
        }
    }

    RegisterScreenContent(
        name = state.name,
        nameError = if (state.nameError != Constants.MINUS_ONE) state.nameError else null,
        onNameChanged = { viewModel.onEvent(RegisterUiEvent.OnNameChanged(it)) },
        email = state.email,
        emailError = if (state.emailError != Constants.MINUS_ONE) state.emailError else null,
        onEmailChanged = { viewModel.onEvent(RegisterUiEvent.OnEmailChanged(it)) },
        password = state.password,
        passwordError = if (state.passwordError != Constants.MINUS_ONE) state.passwordError else null,
        isPasswordVisible = state.passwordVisible,
        onPasswordVisibilityChanged = {
            viewModel.onEvent(RegisterUiEvent.OnPasswordVisibilityChanged(it))
        },
        onPasswordChanged = { viewModel.onEvent(RegisterUiEvent.OnPasswordChanged(it)) },
        passwordConfirmation = state.passwordConfirmation,
        passwordConfirmationError = if (state.passwordConfirmationError != Constants.MINUS_ONE) state.passwordConfirmationError else null,
        onPasswordConfirmationChanged = {
            viewModel.onEvent(
                RegisterUiEvent.OnPasswordConfirmationChanged(
                    it
                )
            )
        },
        onPasswordConfirmationVisibilityChanged = {
            viewModel.onEvent(
                RegisterUiEvent.OnPasswordConfirmationVisibilityChanged(
                    it
                )
            )
        },
        onRegisterClicked = { viewModel.onEvent(RegisterUiEvent.OnSignUp) },
        isRegisterButtonEnabled = state.registerButtonEnabled,
        onSignInClicked = {
            navController.navigateUp()
        }
    )
}

@Composable
fun RegisterScreenContent(
    name: String,
    nameError: Int? = null,
    onNameChanged: (String) -> Unit,
    email: String,
    emailError: Int? = null,
    onEmailChanged: (String) -> Unit,
    password: String,
    passwordError: Int? = null,
    isPasswordVisible: Boolean = false,
    onPasswordVisibilityChanged: (Boolean) -> Unit,
    onPasswordChanged: (String) -> Unit,
    passwordConfirmation: String,
    passwordConfirmationError: Int? = null,
    onPasswordConfirmationChanged: (String) -> Unit,
    onPasswordConfirmationVisibilityChanged: (Boolean) -> Unit,
    onRegisterClicked: () -> Unit,
    isRegisterButtonEnabled: Boolean = false,
    onSignInClicked: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            RegisterTitle(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(top = 24.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
        }

        item {
            CustomTextInput(
                modifier = Modifier.padding(horizontal = 24.dp),
                label = stringResource(id = R.string.input_name_label_text),
                hint = stringResource(id = R.string.input_name_hint_text),
                value = name,
                onValueChanged = onNameChanged,
                leadingIcon = painterResource(id = R.drawable.ic_person),
                errorText = nameError.orDefault(Constants.MINUS_ONE),
                isError = nameError != null,
                imeAction = ImeAction.Next,
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )
            Spacer(modifier = Modifier.height(30.dp))
        }

        item {
            CustomTextInput(
                modifier = Modifier.padding(horizontal = 24.dp),
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
                errorText = emailError.orDefault(Constants.MINUS_ONE)
            )
            Spacer(modifier = Modifier.height(30.dp))
        }

        item {
            CustomTextInput(
                modifier = Modifier.padding(horizontal = 24.dp),
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

                    IconButton(onClick = { onPasswordVisibilityChanged.invoke(!isPasswordVisible) }) {
                        Icon(
                            iconType,
                            contentDescription = contentDescription,
                            tint = HintTextInputColor
                        )
                    }
                },
                imeAction = ImeAction.Next,
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                keyboardType = KeyboardType.Password,
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                isError = passwordError != null,
                errorText = passwordError.orDefault(Constants.MINUS_ONE)
            )
            Spacer(modifier = Modifier.height(30.dp))
        }

        item {
            CustomTextInput(
                modifier = Modifier.padding(horizontal = 24.dp),
                label = stringResource(id = R.string.input_password_confirmation_label_text),
                hint = stringResource(id = R.string.input_password_confirmation_label_text),
                value = passwordConfirmation,
                onValueChanged = onPasswordConfirmationChanged,
                leadingIcon = painterResource(id = R.drawable.ic_password),
                trailingIcon = {
                    val iconType =
                        if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    val contentDescription =
                        if (isPasswordVisible) "Hide Password" else "Show Password"

                    IconButton(onClick = { onPasswordConfirmationVisibilityChanged.invoke(!isPasswordVisible) }) {
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
                isError = passwordConfirmationError != null,
                errorText = passwordConfirmationError.orDefault(Constants.MINUS_ONE)
            )
            Spacer(modifier = Modifier.height(30.dp))
        }

        item {
            CustomButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                title = stringResource(id = R.string.sign_up_button_text),
                onButtonClicked = onRegisterClicked,
                enabled = isRegisterButtonEnabled
            )
            Spacer(modifier = Modifier.height(30.dp))
        }

        item {
            CustomSpannableLinkText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 24.dp),
                fullText = stringResource(id = R.string.already_have_an_account_text),
                linkText = stringResource(id = R.string.sign_in_button_text),
                textAlign = TextAlign.Center,
                textStyle = MaterialTheme.typography.body2.copy(
                    fontWeight = FontWeight.Medium
                ),
                onLinkTextClicked = onSignInClicked
            )
        }
    }
}

@Composable
private fun RegisterTitle(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        CustomSpannableLinkText(
            fullText = stringResource(id = R.string.register_welcome_title_text),
            linkText = stringResource(id = R.string.app_title)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.register_welcome_subtitle_text),
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.body1
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun RegisterScreenPreview() {
    AskMeTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            RegisterScreenContent(
                name = "name",
                onNameChanged = { },
                email = "email",
                onEmailChanged = { },
                password = "password",
                onPasswordChanged = { },
                passwordConfirmation = "password_confirmation",
                onPasswordConfirmationChanged = { },
                onRegisterClicked = { },
                isRegisterButtonEnabled = true,
                onSignInClicked = { },
                nameError = null,
                passwordError = null,
                passwordConfirmationError = null,
                emailError = null,
                isPasswordVisible = false,
                onPasswordVisibilityChanged = { },
                onPasswordConfirmationVisibilityChanged = { }
            )
        }
    }
}