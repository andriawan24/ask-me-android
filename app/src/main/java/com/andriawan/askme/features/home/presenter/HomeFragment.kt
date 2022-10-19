package com.andriawan.askme.features.home.presenter

import android.text.Spannable
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import androidx.core.text.buildSpannedString
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.andriawan.askme.R
import com.andriawan.askme.base.BaseFragment
import com.andriawan.askme.databinding.FragmentHomeBinding
import com.andriawan.askme.domain.models.TopicModel
import com.andriawan.askme.domain.models.UserModel
import com.andriawan.askme.features.home.viewmodel.HomeViewModel
import com.andriawan.askme.utils.Constants.COMMA_WITH_SPACE
import com.andriawan.askme.utils.Constants.UNAUTHORIZED
import com.andriawan.askme.utils.Constants.getGreetings
import com.andriawan.askme.utils.ResultState
import com.andriawan.askme.utils.extensions.orZero
import com.andriawan.askme.utils.extensions.setGone
import com.andriawan.askme.utils.extensions.setInvisible
import com.andriawan.askme.utils.extensions.setVisible
import com.andriawan.askme.utils.network.getErrorMessage
import com.andriawan.askme.utils.recyclerview.ItemSpacingDecoration
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@AndroidEntryPoint
@FragmentScoped
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModels()
    override val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    private var adapter: TopicsAdapter? = null

    override fun initViews() {
        adapter = TopicsAdapter()
        initRecyclerView()
    }

    private fun initRecyclerView() = with(binding) {
        topicsRecyclerView.addItemDecoration(ItemSpacingDecoration())
        topicsRecyclerView.adapter = adapter
        topicsRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
    }

    override fun initObservers() {
        viewModel.userModel.observe(this, this::observerUserModel)
        viewModel.topicsModel.observe(this, this::observerTopics)
        viewModel.navigateToLogin.observe(this) {
            it.getContentIfNotHandled()?.let {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToLoginFragment()
                )
            }
        }
    }

    private fun observerTopics(state: ResultState<List<TopicModel>>) {
        when (state) {
            is ResultState.Loading -> {
                onShowTopicsLoading()
            }
            is ResultState.Success -> {
                onHideTopicsLoading()
                adapter?.setData(state.data.orEmpty())
            }
            is ResultState.Error -> {
                onHideTopicsLoading()
            }
        }
    }

    private fun onShowTopicsLoading() = with(binding) {
        topicsShimmer.root.startShimmer()
        topicsShimmer.root.setVisible()
        topicsRecyclerView.setInvisible()
    }

    private fun onHideTopicsLoading() = with(binding) {
        topicsShimmer.root.stopShimmer()
        topicsShimmer.root.setGone()
        topicsRecyclerView.setVisible()
    }

    private fun observerUserModel(state: ResultState<UserModel?>) {
        when (state) {
            is ResultState.Loading -> {
                showHeaderLoading()
            }
            is ResultState.Success -> {
                hideHeaderLoading()
                setGreetingsText(state.data?.name.orEmpty())
                setPointsText(state.data?.points.orZero())
                binding.profilePictureImageView.setImageResource(R.drawable.ic_launcher_background)
            }
            is ResultState.Error -> {
                hideHeaderLoading()
                val errorMessage = state.exception.getErrorMessage(requireContext())
                onHeaderError(errorMessage)
            }
        }
    }

    private fun onHeaderError(errorMessage: String) {
        showCustomSnackBar(binding.root, errorMessage)
        if (errorMessage.contains(UNAUTHORIZED, ignoreCase = true)) {
            unauthorizedRequest()
        }
    }

    private fun unauthorizedRequest() {
        viewModel.onUnauthorizedRequest()
    }

    private fun hideHeaderLoading() = with(binding) {
        headerShimmer.root.setGone()
        headerLayout.setVisible()
    }

    private fun showHeaderLoading() = with(binding) {
        headerShimmer.root.showShimmer(true)
        headerShimmer.root.setVisible()
    }

    private fun setPointsText(points: Int) = with(binding) {
        pointTextView.text = getString(R.string.points_template, points)
    }

    private fun setGreetingsText(name: String) = with(binding) {
        sayHelloTextView.text = buildSpannedString {
            val greetings = getString(getGreetings()) + COMMA_WITH_SPACE
            append(greetings)
            append(name)
            setSpan(
                ForegroundColorSpan(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.button_color
                    )
                ),
                greetings.length,
                greetings.length + name.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }
}