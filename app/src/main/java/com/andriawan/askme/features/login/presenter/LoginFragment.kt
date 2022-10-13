package com.andriawan.askme.features.login.presenter

import android.text.Spannable
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.text.buildSpannedString
import androidx.fragment.app.viewModels
import com.andriawan.askme.R
import com.andriawan.askme.base.BaseFragment
import com.andriawan.askme.databinding.FragmentLoginBinding
import com.andriawan.askme.features.login.viewmodel.LoginViewModel
import com.andriawan.askme.utils.Constants.NEW_LINE
import com.andriawan.askme.utils.Constants.ONE
import com.andriawan.askme.utils.Constants.SPACE
import com.andriawan.askme.utils.Constants.ZERO

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    override val viewModel: LoginViewModel by viewModels()
    override val binding: FragmentLoginBinding by lazy {
        FragmentLoginBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        binding.titleTextView.text = createWelcomeText()
        binding.signUpTextView.apply {
            text = createSignUpText()
            movementMethod = LinkMovementMethod.getInstance()
        }
        binding.signInButton.setOnClickListener {
            showCustomSnackBar(
                binding.root,
                "Failed to login"
            )
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
                        "Failed to login"
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