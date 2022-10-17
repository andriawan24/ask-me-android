package com.andriawan.askme.features.register.presenter

import android.text.Spannable
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.text.buildSpannedString
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.andriawan.askme.R
import com.andriawan.askme.base.BaseFragment
import com.andriawan.askme.databinding.FragmentRegisterBinding
import com.andriawan.askme.features.register.viewmodel.RegisterViewModel
import com.andriawan.askme.utils.Constants
import com.andriawan.askme.utils.ResultState
import com.andriawan.askme.utils.extensions.disabledWithText
import com.andriawan.askme.utils.extensions.enableWithText
import com.andriawan.askme.utils.extensions.setErrorText
import com.andriawan.askme.utils.network.getErrorMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>() {

    override val viewModel: RegisterViewModel by viewModels()
    override val binding: FragmentRegisterBinding by lazy {
        FragmentRegisterBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        with(binding) {
            addTitleColor()
            signInTextView.apply {
                text = createSignInText()
                movementMethod = LinkMovementMethod()
            }
            nameTextInput.addTextChangedListener {
                nameTextInputLayout.setErrorText(viewModel.validateName(it))
            }
            emailTextInput.addTextChangedListener {
                emailTextInputLayout.setErrorText(viewModel.validateEmail(it))
            }
            passwordTextInput.addTextChangedListener {
                passwordTextInputLayout.setErrorText(viewModel.validatePassword(it))
                passwordConfirmationTextInputLayout.setErrorText(
                    viewModel.validatePasswordConfirmation(
                        passwordTextInput.text,
                        passwordConfirmationTextInput.text
                    )
                )
            }
            passwordConfirmationTextInput.addTextChangedListener {
                passwordConfirmationTextInputLayout.setErrorText(
                    viewModel.validatePasswordConfirmation(
                        passwordTextInput.text,
                        passwordConfirmationTextInput.text
                    )
                )
            }
            signUpButton.setOnClickListener {
                val name = binding.nameTextInput.text.toString()
                val email = binding.emailTextInput.text.toString()
                val password = binding.passwordTextInput.text.toString()
                viewModel.signUp(name, email, password)
            }
        }
    }

    private fun addTitleColor() {
        binding.titleTextView.text = buildSpannedString {
            append(getString(R.string.register_title))
            val appName = getString(R.string.app_title)
            val appNameIndex = binding.titleTextView.text.indexOf(appName, ignoreCase = true)
            setSpan(
                ForegroundColorSpan(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.button_color
                    )
                ),
                appNameIndex,
                appNameIndex + appName.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }

    private fun createSignInText() = buildSpannedString {
        val signInText = getString(R.string.sign_in_text)
        append(signInText)
        append(Constants.SPACE)
        append(getString(R.string.sign_in))
        setSpan(
            object : ClickableSpan() {
                override fun onClick(widget: View) {
                    findNavController().navigate(
                        RegisterFragmentDirections
                            .actionRegisterFragmentToLoginFragment()
                    )
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = false
                }
            },
            signInText.length.plus(Constants.ONE),
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
            signInText.length.plus(Constants.ONE),
            this.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }

    override fun initObservers() {
        viewModel.signUpResponse.observe(this) {
            when (it) {
                is ResultState.Loading -> {
                    binding.signUpButton.disabledWithText(R.string.loading)
                }

                is ResultState.Error -> {
                    binding.signUpButton.enableWithText(R.string.sign_up)
                    showCustomSnackBar(
                        binding.root,
                        it.exception.getErrorMessage(requireContext())
                    )
                }

                is ResultState.Success -> {
                    binding.signUpButton.enableWithText(R.string.sign_up)
                    showCustomSnackBar(
                        binding.root,
                        getString(R.string.register_successful),
                        Constants.AlertType.SUCCESS
                    )
                    setFragmentResult(
                        REGISTER_RESULT, bundleOf(
                            EXTRA_EMAIL to it.data?.email.orEmpty()
                        )
                    )
                    findNavController().popBackStack()
                }
            }
        }
    }

    companion object {
        const val EXTRA_EMAIL = "extra_email"
        const val REGISTER_RESULT = "register_result"
    }
}