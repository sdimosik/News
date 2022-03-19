package com.sdimosikvip.news.ui.home

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.RequestManager
import com.sdimosikvip.news.R
import com.sdimosikvip.news.base.BaseFragment
import com.sdimosikvip.news.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FragmentHome : BaseFragment(R.layout.fragment_home) {

    override val binding by viewBinding(FragmentHomeBinding::bind)
    private val homeViewModel by viewModels<HomeViewModel>()

    @Inject
    lateinit var glide: RequestManager

    private val homeAdapter by lazy {
        MainHomeAdapter(glide, homeViewModel.scrollStates)
    }

    override fun setupViews() {
        super.setupViews()

        with(binding) {
            recyclerView.apply {
                swapAdapter(homeAdapter, true)
                setHasFixedSize(true)
                setItemViewCacheSize(20)
            }
        }
    }

    override fun subscribe() {
        super.subscribe()

        homeViewModel.list.observe(viewLifecycleOwner) {
            homeAdapter.apply {
                items = it
            }
        }
    }
}