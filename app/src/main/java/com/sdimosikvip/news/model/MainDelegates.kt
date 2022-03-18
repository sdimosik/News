package com.sdimosikvip.news.model

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.sdimosikvip.news.base.BaseModel
import com.sdimosikvip.news.base.StartSnapHelper
import com.sdimosikvip.news.databinding.ItemNewsBinding
import com.sdimosikvip.news.databinding.LayoutHorizontalRecyclerWithViewBinding

object MainDelegates {

    val newsHorizontal =
        adapterDelegateViewBinding<ItemListNews, BaseModel, LayoutHorizontalRecyclerWithViewBinding>(
            { layoutInflater, parent ->
                LayoutHorizontalRecyclerWithViewBinding.inflate(layoutInflater, parent, false)
                    .apply {
                        recyclerView.adapter = ListDelegationAdapter(
                            newsDelegate
                        )
                    }
            }) {
            bind {
                val snapHelper = StartSnapHelper()
                snapHelper.attachToRecyclerView(binding.recyclerView)
                binding.tittle.text = item.tittle
                (binding.recyclerView.adapter as ListDelegationAdapter<*>).apply {
                    items = item.list
                    notifyDataSetChanged()
                }
            }
        }

    val newsDelegate = adapterDelegateViewBinding<ItemNews, BaseModel, ItemNewsBinding>(
        { layoutInflater, parent -> ItemNewsBinding.inflate(layoutInflater, parent, false) }
    ) {
        bind {
            binding.tittle.text = item.tittle
        }
    }
}