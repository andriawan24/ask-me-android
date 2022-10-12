package com.andriawan.askme.features.login.presenter

import android.text.Spannable
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import androidx.core.text.buildSpannedString
import androidx.fragment.app.viewModels
import com.andriawan.askme.R
import com.andriawan.askme.base.BaseFragment
import com.andriawan.askme.databinding.FragmentLoginBinding
import com.andriawan.askme.features.login.viewmodel.LoginViewModel
import com.andriawan.askme.utils.Constants.NEW_LINE
import com.andriawan.askme.utils.Constants.ZERO

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    override val viewModel: LoginViewModel by viewModels()
    override val binding: FragmentLoginBinding by lazy {
        FragmentLoginBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        binding.titleTextView.text = createWelcomeText()
    }

    private fun createWelcomeText() = buildSpannedString {
        val welcomeText = getString(R.string.welcome_text)
        append(welcomeText)
        append(NEW_LINE)
        val appName = getString(R.string.app_name)
        append(appName)
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
}