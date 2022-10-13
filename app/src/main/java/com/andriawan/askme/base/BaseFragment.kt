package com.andriawan.askme.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.andriawan.askme.databinding.ViewCustomSnackbarBinding
import com.andriawan.askme.utils.Constants
import com.andriawan.askme.utils.Constants.EMPTY
import com.andriawan.askme.utils.Constants.ZERO
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.SnackbarLayout

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel> : Fragment() {

    abstract val binding: VB
    abstract val viewModel: VM

    private var hasInitialized = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onPause() {
        super.onPause()
        hasInitialized = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!hasInitialized) {
            initViews()
        }
    }

    fun showCustomSnackBar(
        container: View,
        message: String,
        type: Constants.AlertType = Constants.AlertType.ERROR
    ) {
        val snackBar = Snackbar.make(container, EMPTY, Snackbar.LENGTH_SHORT)
        val snackBarLayout = ViewCustomSnackbarBinding.inflate(layoutInflater).apply {
            messageTextView.text = message
            backgroundMaterialCardView.setOnClickListener {
                snackBar.dismiss()
            }
        }

        val snackBarView = snackBar.view as SnackbarLayout
        snackBarView.setPadding(ZERO, ZERO, ZERO, ZERO)
        snackBarView.addView(snackBarLayout.root, ZERO)
        snackBar.show()
    }

    abstract fun initViews()
}