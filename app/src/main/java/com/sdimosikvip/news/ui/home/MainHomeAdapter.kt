package com.sdimosikvip.news.ui.home

import android.os.Parcelable
import android.widget.AbsListView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.sdimosikvip.common.model.AvailableCategory
import com.sdimosikvip.news.base.BaseDiffModel
import com.sdimosikvip.news.base.BaseDiffUtilItemCallback
import com.sdimosikvip.news.base.BaseViewModel
import com.sdimosikvip.news.model.ItemNews

class MainHomeAdapter(
    glide: RequestManager,
    scrollStates: MutableMap<Int, Parcelable>,
    advancedLoading: LiveData<Boolean>,
    onReadyToLoadMore: (AvailableCategory, Int, Int) -> Unit,
    onClick: (ItemNews) -> Unit
) : AsyncListDifferDelegationAdapter<BaseDiffModel>(BaseDiffUtilItemCallback()) {

    private val sharedViewPool: RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()

    init {
        delegatesManager.addDelegate(
            MainDelegates.newsHorizontalDelegate(
                glide,
                scrollStates,
                sharedViewPool,
                advancedLoading,
                onReadyToLoadMore,
                onClick
            )
        )
    }
}