package com.sdimosikvip.news.ui.home

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.sdimosikvip.news.base.BaseDiffModel
import com.sdimosikvip.news.base.BaseDiffUtilItemCallback

class MainHomeAdapter :
    AsyncListDifferDelegationAdapter<BaseDiffModel>(BaseDiffUtilItemCallback()) {
    init {
        delegatesManager.addDelegate(MainDelegates.newsHorizontalDelegate)
    }
}