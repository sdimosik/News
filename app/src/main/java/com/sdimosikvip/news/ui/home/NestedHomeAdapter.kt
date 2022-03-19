package com.sdimosikvip.news.ui.home

import com.bumptech.glide.RequestManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.sdimosikvip.news.base.BaseDiffModel
import com.sdimosikvip.news.base.BaseDiffUtilItemCallback
import com.sdimosikvip.news.model.ItemNews

class NestedHomeAdapter(
    glide: RequestManager,
    onClick: (ItemNews) -> Unit
) : AsyncListDifferDelegationAdapter<BaseDiffModel>(BaseDiffUtilItemCallback()) {
    init {
        delegatesManager
            .addDelegate(MainDelegates.newsDelegate(glide, onClick))
            .addDelegate(MainDelegates.progressNewsDelegate())
    }
}