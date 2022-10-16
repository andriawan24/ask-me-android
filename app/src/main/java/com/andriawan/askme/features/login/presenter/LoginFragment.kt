package com.andriawan.askme.features.login.presenter

import android.text.Spannable
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.util.Patterns
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.text.buildSpannedString
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.andriawan.askme.R
import com.andriawan.askme.base.BaseFragment
import com.andriawan.askme.databinding.FragmentLoginBinding
import com.andriawan.askme.features.login.viewmodel.LoginViewModel
import com.andriawan.askme.utils.Constants.AlertType.SUCCESS
import com.andriawan.askme.utils.Constants.MAXIMUM_PASSWORD_LENGTH
import com.andriawan.askme.utils.Constants.MINIMUM_PASSWORD_LENGTH
import com.andriawan.askme.utils.Constants.NEW_LINE
import com.andriawan.askme.utils.Constants.ONE
import com.andriawan.askme.utils.Constants.SPACE
import com.andriawan.askme.utils.Constants.ZERO
import com.andriawan.askme.utils.ResultState
import com.andriawan.askme.utils.disabledWithText
import com.andriawan.askme.utils.enableWithText
import com.andriawan.askme.utils.network.getErrorMessage
import com.andriawan.askme.utils.setErrorText
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import java.util.regex.Pattern

@AndroidEntryPoint
@FragmentScoped
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    override val viewModel: LoginViewModel by viewModels()
    override val binding: FragmentLoginBinding by lazy {
        FragmentLoginBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        binding.titleTextView.text = createWelcomeText()
        binding.emailTextInput.addTextChangedListener {
            if (it.isNullOrBlank()) {
                binding.emailTextInputLayout.setErrorText(getString(R.string.empty_email))
            } else if (!Pattern.matches(Patterns.EMAIL_ADDRESS.pattern(), it)) {
                binding.emailTextInputLayout.setErrorText(getString(R.string.not_valid_email))
            } else {
                binding.emailTextInputLayout.isErrorEnabled = false
            }
        }
        binding.passwordTextInput.addTextChangedListener {
            if (it.isNullOrBlank()) {
                binding.passwordTextInputLayout.setErrorText(getString(R.string.empty_password))
                binding.passwordTextInputLayout.errorIconDrawable = null
            } else if (it.length !in MINIMUM_PASSWORD_LENGTH..MAXIMUM_PASSWORD_LENGTH) {
                binding.passwordTextInputLayout.setErrorText(getString(R.string.length_password))
                binding.passwordTextInputLayout.errorIconDrawable = null
            } else {
                binding.passwordTextInputLayout.isErrorEnabled = false
            }
        }
        binding.signUpTextView.apply {
            text = createSignUpText()
            movementMethod = LinkMovementMethod.getInstance()
        }
        binding.signInButton.setOnClickListener {
            val email = binding.emailTextInput.text.toString()
            val password = binding.passwordTextInput.text.toString()
            viewModel.signIn(email, password)
        }
    }

    override fun initObservers() {
        viewModel.signInResult.observe(this) {
            when (it) {
                is ResultState.Loading -> {
                    binding.signInButton.disabledWithText(R.string.loading)
                }

                is ResultState.Error -> {
                    binding.signInButton.enableWithText(R.string.sign_in)
                    showCustomSnackBar(
                        binding.root,
                        it.exception.getErrorMessage(requireContext())
                    )
                }

                is ResultState.Success -> {
                    binding.signInButton.enableWithText(R.string.sign_in)
                    showCustomSnackBar(
                        binding.root,
                        getString(R.string.login_successful),
                        SUCCESS
                    )
                }
            }
        }
    }

    private fun createWelcomeText() = buildSpannedString {
        val welcomeText = getString(R.string.welcome_text)
        append(welcomeText)
        append(NEW_LINE)
        append(getString(R.string.app_name))
        setSpan(
            ForegroundColorSpan(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.primary_text_color
                )
            ),
            ZERO,
            welcomeText.lastIndex,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        setSpan(
            ForegroundColorSpan(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.button_color
                )
            ),
            welcomeText.length,
            this.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }

    private fun createSignUpText() = buildSpannedString {
        val signUpText = getString(R.string.sign_up_text)
        append(signUpText)
        append(SPACE)
        append(getString(R.string.sign_up))
        setSpan(
            object : ClickableSpan() {
                override fun onClick(widget: View) {
                    showCustomSnackBar(
                        binding.root,
                        "Go to register page",
                        SUCCESS
                    )
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = false
                }
            },
            signUpText.length.plus(ONE),
            this.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        setSpan(
            ForegroundColorSpan(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.button_color
                )
            ),
            signUpText.length.plus(ONE),
            this.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
}