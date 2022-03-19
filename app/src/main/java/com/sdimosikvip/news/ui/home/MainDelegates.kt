package com.sdimosikvip.news.ui.home

import android.app.Activity
import android.os.Parcelable
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
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
        onClick: (ItemNews) -> Unit
    ) =
        adapterDelegateViewBinding<ItemListNews, BaseDiffModel, LayoutHorizontalRecyclerWithViewBinding>(
            viewBinding = { layoutInflater, parent ->
                LayoutHorizontalRecyclerWithViewBinding.inflate(layoutInflater, parent, false)
            },
        ) {

            // onCreateViewHolder
            val snapHelper = StartSnapHelper()
            val nestedAdapter = NestedHomeAdapter(glide, onClick)
            binding.recyclerView.apply {
                setRecycledViewPool(sharedViewPool)
                adapter = nestedAdapter
                setHasFixedSize(true)
                setItemViewCacheSize(10)
            }

            bind {
                snapHelper.attachToRecyclerView(binding.recyclerView)
                binding.tittle.text = item.tittle
                nestedAdapter.apply {
                    items = item.list
                }
            }

            onViewRecycled {
                binding.recyclerView.layoutManager?.onSaveInstanceState()?.let {
                    scrollStates[bindingAdapterPosition] = it
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

            bind {
                val resources = binding.root.resources
                binding.tittle.text = item.tittle
                binding.time.text = item.timestampString
                glide.load(item.urlImg)
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