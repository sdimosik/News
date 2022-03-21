package com.sdimosikvip.news.ui.home

import android.app.Activity
import android.graphics.drawable.Drawable
import android.os.Parcelable
import android.view.animation.AnimationUtils
import android.widget.AbsListView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.sdimosikvip.common.model.AvailableCategory
import com.sdimosikvip.news.R
import com.sdimosikvip.news.base.BaseDiffModel
import com.sdimosikvip.news.base.StartSnapHelper
import com.sdimosikvip.news.databinding.ItemNewsBinding
import com.sdimosikvip.news.databinding.ItemNewsProgressBinding
import com.sdimosikvip.news.databinding.LayoutHorizontalRecyclerWithViewBinding
import com.sdimosikvip.news.model.ItemListNews
import com.sdimosikvip.news.model.ItemNews
import com.sdimosikvip.news.model.ProgressItemNews


object MainDelegates {

    fun newsHorizontalDelegate(
        glide: RequestManager,
        scrollStates: MutableMap<Int, Parcelable>,
        sharedViewPool: RecyclerView.RecycledViewPool,
        advancedLoading: LiveData<Boolean>,
        onReadyToLoadMore: (AvailableCategory, Int, Int) -> Unit,
        onClick: (ItemNews) -> Unit
    ) =
        adapterDelegateViewBinding<ItemListNews, BaseDiffModel, LayoutHorizontalRecyclerWithViewBinding>(
            viewBinding = { layoutInflater, parent ->
                LayoutHorizontalRecyclerWithViewBinding.inflate(layoutInflater, parent, false)
            },
        ) {

            var isLastPage = false
            var isScrolling = false
            var page = 2
            val pageSize = 20

            val scrollListener = object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount

                    val isNotLoadingAndNotLastPage = !advancedLoading.value!!
                    val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
                    val isNotAtBeginning = firstVisibleItemPosition >= 0
                    val isTotalMoreThanVisible = totalItemCount >= pageSize
                    val shouldPaginate =
                        isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning &&
                                isTotalMoreThanVisible && isScrolling
                    if (shouldPaginate) {
                        onReadyToLoadMore(item.category, page, pageSize)
                        page++
                        isScrolling = false
                    }
                }

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                        isScrolling = true
                    }
                }
            }
            val snapHelper = StartSnapHelper()
            val nestedAdapter = NestedHomeAdapter(glide, onClick)
            binding.recyclerView.apply {
                setRecycledViewPool(sharedViewPool)
                adapter = nestedAdapter
                setHasFixedSize(true)
                addOnScrollListener(scrollListener)
            }
            binding.recyclerView.itemAnimator = null

            bind {
                snapHelper.attachToRecyclerView(binding.recyclerView)
                binding.tittle.text = item.tittle
                nestedAdapter.apply {
                    items = item.list
                }
            }
        }

    fun newsDelegate(
        glide: RequestManager,
        onClick: (ItemNews) -> Unit
    ) =
        adapterDelegateViewBinding<ItemNews, BaseDiffModel, ItemNewsBinding>(
            { layoutInflater, parent -> ItemNewsBinding.inflate(layoutInflater, parent, false) }
        ) {

            binding.root.setOnClickListener {
                onClick(item)
            }
            val requestBuilder: RequestBuilder<Drawable> =
                Glide.with(binding.root.context)
                    .asDrawable().sizeMultiplier(0.05f)

            bind {
                val resources = binding.root.resources
                binding.tittle.text = item.tittle
                binding.time.text = item.timestampString

                glide.load(item.urlImg)
                    .thumbnail(requestBuilder)
                    .transform(
                        CenterCrop(),
                        RoundedCorners(resources.getDimensionPixelOffset(R.dimen.news_item_radius))
                    )
                    .transition(withCrossFade())
                    .placeholder(R.drawable.news_placeholder)
                    .into(binding.imageView)
            }

            onViewRecycled {
                if ((binding.imageView.context as? Activity)?.isDestroyed?.not() == true) {
                    glide.clear(binding.imageView)
                }
            }
        }

    fun progressNewsDelegate() =
        adapterDelegateViewBinding<ProgressItemNews, BaseDiffModel, ItemNewsProgressBinding>(
            { layoutInflater, parent ->
                ItemNewsProgressBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            }
        ) {
            val animation =
                AnimationUtils.loadAnimation(binding.root.context, R.anim.progress_fade_out)
            binding.root.startAnimation(animation)
        }
}