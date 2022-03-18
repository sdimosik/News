package com.sdimosikvip.news.ui.home

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.sdimosikvip.news.base.BaseDiffModel
import com.sdimosikvip.news.base.StartSnapHelper
import com.sdimosikvip.news.databinding.ItemNewsBinding
import com.sdimosikvip.news.databinding.ItemNewsProgressBinding
import com.sdimosikvip.news.databinding.LayoutHorizontalRecyclerWithViewBinding
import com.sdimosikvip.news.model.ItemListNews
import com.sdimosikvip.news.model.ItemNews
import com.sdimosikvip.news.model.ProgressItemNews

object MainDelegates {

    val newsHorizontalDelegate =
        adapterDelegateViewBinding<ItemListNews, BaseDiffModel, LayoutHorizontalRecyclerWithViewBinding>(
            { layoutInflater, parent ->
                LayoutHorizontalRecyclerWithViewBinding.inflate(layoutInflater, parent, false)
            }) {

            // onCreateViewHolder
            val snapHelper = StartSnapHelper()
            val adapter = NestedHomeAdapter()
            binding.recyclerView.adapter = adapter

            bind {
                snapHelper.attachToRecyclerView(binding.recyclerView)
                binding.tittle.text = item.tittle
                adapter.apply {
                    items = item.list
                }
            }
        }

    val newsDelegate = adapterDelegateViewBinding<ItemNews, BaseDiffModel, ItemNewsBinding>(
        { layoutInflater, parent -> ItemNewsBinding.inflate(layoutInflater, parent, false) }
    ) {
        bind {
            binding.tittle.text = item.tittle
        }
    }

    val progressNewsDelegate =
        adapterDelegateViewBinding<ProgressItemNews, BaseDiffModel, ItemNewsProgressBinding>(
            { layoutInflater, parent ->
                ItemNewsProgressBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            }
        ) {
            bind {
                binding.root.startShimmer()
            }
        }
}