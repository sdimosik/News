package com.sdimosikvip.news.ui.home

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.sdimosikvip.news.base.BaseDiffModel
import com.sdimosikvip.news.base.BaseDiffUtilItemCallback

class NestedHomeAdapter :
    AsyncListDifferDelegationAdapter<BaseDiffModel>(BaseDiffUtilItemCallback()) {
    init {
        delegatesManager
            .addDelegate(MainDelegates.newsDelegate)
            .addDelegate(MainDelegates.progressNewsDelegate)
    }
}