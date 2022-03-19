package com.sdimosikvip.news.ui.home

import com.bumptech.glide.RequestManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.sdimosikvip.news.base.BaseDiffModel
import com.sdimosikvip.news.base.BaseDiffUtilItemCallback

class NestedHomeAdapter(
    glide: RequestManager
) : AsyncListDifferDelegationAdapter<BaseDiffModel>(BaseDiffUtilItemCallback()) {
    init {
        delegatesManager
            .addDelegate(MainDelegates.newsDelegate(glide))
            .addDelegate(MainDelegates.progressNewsDelegate())
    }
}