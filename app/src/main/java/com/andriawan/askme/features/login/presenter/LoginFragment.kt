package com.andriawan.askme.features.login.presenter

import android.text.Spannable
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.text.buildSpannedString
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.andriawan.askme.R
import com.andriawan.askme.base.BaseFragment
import com.andriawan.askme.databinding.FragmentLoginBinding
import com.andriawan.askme.features.login.viewmodel.LoginViewModel
import com.andriawan.askme.features.register.presenter.RegisterFragment.Companion.EXTRA_EMAIL
import com.andriawan.askme.features.register.presenter.RegisterFragment.Companion.REGISTER_RESULT
import com.andriawan.askme.utils.Constants.AlertType.SUCCESS
import com.andriawan.askme.utils.Constants.NEW_LINE
import com.andriawan.askme.utils.Constants.ONE
import com.andriawan.askme.utils.Constants.SPACE
import com.andriawan.askme.utils.Constants.ZERO
import com.andriawan.askme.utils.ResultState
import com.andriawan.askme.utils.extensions.disabledWithText
import com.andriawan.askme.utils.extensions.enableWithText
import com.andriawan.askme.utils.extensions.setErrorText
import com.andriawan.askme.utils.network.getErrorMessage
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@AndroidEntryPoint
@FragmentScoped
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    override val viewModel: LoginViewModel by viewModels()
    override val binding: FragmentLoginBinding by lazy {
        FragmentLoginBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        with(binding) {
            titleTextView.text = createWelcomeText()
            emailTextInput.doAfterTextChanged {
                emailTextInputLayout.setErrorText(viewModel.validateEmail(it))
            }
            passwordTextInput.doAfterTextChanged {
                passwordTextInputLayout.setErrorText(viewModel.validatePassword(it))
                passwordTextInputLayout.errorIconDrawable = null
            }
            signUpTextView.apply {
                text = createSignUpText()
                movementMethod = LinkMovementMethod.getInstance()
            }
            signInButton.setOnClickListener {
                val email = emailTextInput.text.toString()
                val password = passwordTextInput.text.toString()
                viewModel.signIn(email, password)
            }

            setFragmentResultListener(REGISTER_RESULT) { _, bundle ->
                if (bundle.getString(EXTRA_EMAIL).isNullOrBlank().not()) {
                    binding.emailTextInput.setText(bundle.getString(EXTRA_EMAIL))
                }
            }
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
                    findNavController().navigate(
                        LoginFragmentDirections
                            .actionLoginFragmentToRegisterFragment()
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