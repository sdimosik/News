package com.sdimosikvip.news.ui.home

import android.os.Parcelable
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.sdimosikvip.news.base.BaseDiffModel
import com.sdimosikvip.news.base.BaseDiffUtilItemCallback
import com.sdimosikvip.news.model.ItemListNews
import com.sdimosikvip.news.model.ItemNews
import java.util.*

class MainHomeAdapter(
    glide: RequestManager,
    scrollStates: MutableMap<Int, Parcelable>,
    onClick: (ItemNews) -> Unit
) : AsyncListDifferDelegationAdapter<BaseDiffModel>(BaseDiffUtilItemCallback()), Filterable {

    private val sharedViewPool: RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()
    private var filterList: List<ItemListNews> = mutableListOf()

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

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val input = constraint.toString().lowercase(Locale.getDefault())
                if (input.isEmpty()) {
                    filterList = items as List<ItemListNews>
                } else {
                    val result = mutableListOf<ItemListNews>()
                    for (itemListNews in items as List<ItemListNews>) {
                        val innerResult = mutableListOf<ItemNews>()
                        val innerList = itemListNews.list as List<ItemNews>
                        for (news in innerList) {
                            if (news.tittle.lowercase(Locale.getDefault()).contains(input)
                                || news.description.lowercase(Locale.getDefault()).contains(input)
                            ) {
                                innerResult.add(news)
                            }
                        }
                        if (innerResult.isNotEmpty()) result.add(
                            ItemListNews(
                                result,
                                itemListNews.tittle
                            )
                        )
                    }
                    filterList = result
                }
                val result = FilterResults()
                result.values = filterList
                return result
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterList = results?.values as List<ItemListNews>
                notifyDataSetChanged()
            }
        }
    }
}