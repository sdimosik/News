package com.sdimosikvip.news.ui.home

import android.view.View
import android.widget.AbsListView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.RequestManager
import com.sdimosikvip.news.R
import com.sdimosikvip.news.base.BaseFragment
import com.sdimosikvip.news.base.BaseViewModel
import com.sdimosikvip.news.base.StartSnapHelper
import com.sdimosikvip.news.databinding.FragmentHomeBinding
import com.sdimosikvip.news.utils.extensions.browse
import com.sdimosikvip.news.utils.extensions.fadeVisibility
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class FragmentHome : BaseFragment(R.layout.fragment_home) {

    companion object {
        const val TAG = "FragmentHome"
    }


    override val binding by viewBinding(FragmentHomeBinding::bind)
    private val homeViewModel by viewModels<HomeViewModel>()

    @Inject
    lateinit var glide: RequestManager

    private val homeAdapter by lazy {
        MainHomeAdapter(
            glide,
            homeViewModel.scrollStates,
            homeViewModel.advancedLoading,
            onReadyToLoadMore = { category, page, pageSize -> homeViewModel.loadMore(category, page, pageSize) },
            onClick = { requireContext().browse(it.urlRedirect) })
    }

    private val snapHelper by lazy {
        StartSnapHelper()
    }

    override fun setupViews() {
        super.setupViews()

        with(binding) {
            recyclerView.apply {
                swapAdapter(homeAdapter, true)
                setHasFixedSize(true)
                setItemViewCacheSize(20)
                itemAnimator = null
            }

            buttonTryAgain.setOnClickListener {
                val state = homeViewModel.state.value
                if (state is BaseViewModel.State.IsLoading && state.isLoading) {
                    return@setOnClickListener
                }

                homeViewModel.update()
            }
        }
    }

    override fun subscribe() {
        super.subscribe()

        homeViewModel.news.observe(viewLifecycleOwner) {
            controlIfDataEmpty(it.isEmpty())
            homeAdapter.apply {
                items = it
            }
        }

        homeViewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is BaseViewModel.State.Init -> {
                    Timber.tag(TAG).d("state: init")
                }
                is BaseViewModel.State.IsLoading -> {
                    Timber.tag(TAG).d("state: loading ${state.isLoading}")
                }
            }
        }

        homeViewModel.action.observe(viewLifecycleOwner) { action ->
            when (action) {
                is BaseViewModel.Action.ShowToast -> {
                    showError(getString(action.messageRes))
                }
            }
        }
    }

    private fun controlIfDataEmpty(isEmpty: Boolean) {
        if (isEmpty) {
            binding.recyclerView.fadeVisibility(View.INVISIBLE)
            binding.cardEmptyData.fadeVisibility(View.VISIBLE)
            binding.buttonTryAgain.fadeVisibility(View.VISIBLE)
        } else {
            binding.recyclerView.fadeVisibility(View.VISIBLE)
            binding.cardEmptyData.fadeVisibility(View.INVISIBLE)
            binding.buttonTryAgain.fadeVisibility(View.INVISIBLE)
        }
    }
}