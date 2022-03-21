package com.sdimosikvip.news.ui.home

import android.os.Parcelable
import android.widget.AbsListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.sdimosikvip.news.base.BaseDiffModel
import com.sdimosikvip.news.base.BaseDiffUtilItemCallback
import com.sdimosikvip.news.model.ItemNews

class MainHomeAdapter(
    glide: RequestManager,
    scrollStates: MutableMap<Int, Parcelable>,
    onClick: (ItemNews) -> Unit
) : AsyncListDifferDelegationAdapter<BaseDiffModel>(BaseDiffUtilItemCallback()) {

    private val sharedViewPool: RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()

    init {
        delegatesManager.addDelegate(
            MainDelegates.newsHorizontalDelegate(
                glide,
                scrollStates,
                sharedViewPool,
                onClick
            )
        )
    }
}