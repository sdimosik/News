package com.sdimosikvip.news.ui.home

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sdimosikvip.common.model.AvailableCategory
import com.sdimosikvip.domain.interactor.NewsInteractor
import com.sdimosikvip.news.base.BaseViewModel
import com.sdimosikvip.news.mapper.newsDomainToItemNews
import com.sdimosikvip.news.model.ItemListNews
import com.sdimosikvip.news.model.ProgressItemNews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsInteractor: NewsInteractor,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseViewModel() {

    private val _news = MutableLiveData<List<ItemListNews>>()
    val news: LiveData<List<ItemListNews>> = _news
    val scrollStates = mutableMapOf<Int, Parcelable>()

    private val _advancedLoading = MutableLiveData(false)
    val advancedLoading: LiveData<Boolean> = _advancedLoading

    init {
        init()
    }

    private fun init() {
        viewModelScope.launch(handlerException) {
            withContext(defaultDispatcher) {
                _news.postValue(getLoaderItems())
                val items = getItems()
                _news.postValue(items)
                hideLoading()
            }
        }
    }

    fun update() {
        viewModelScope.launch(handlerException) {
            withContext(defaultDispatcher) {
                setLoading()
                val items = getItems()
                _news.postValue(items)
                hideLoading()
            }
        }
    }


    fun loadMore(category: AvailableCategory, page: Int, pageSize: Int) {
        viewModelScope.launch(handlerException) {
            withContext(defaultDispatcher) {
                val items =
                    newsDomainToItemNews(newsInteractor.getOneHotNews(page, pageSize, category))
                synchronized(HomeViewModel::class) {
                    _advancedLoading.postValue(true)
                    val list = _news.value!!
                    var res: ItemListNews? = null
                    for (i in 0 until list.size) {
                        if (list[i].category != category) continue
                        res = ItemListNews(
                            list[i].list.plus(items.list),
                            list[i].tittle,
                            list[i].category
                        )
                    }

                    if (res != null) {
                        val newList: MutableList<ItemListNews> = mutableListOf()
                        for (i in 0 until list.size) {
                            if (list[i].category != category) {
                                newList.add(list[i])
                            } else {
                                newList.add(res)
                            }
                        }
                        _news.postValue(newList)
                    }
                    _advancedLoading.postValue(false)
                }
            }
        }
    }

    private suspend fun getItems(): List<ItemListNews> {
        val result = newsInteractor.initNews()
        return result.map { newsDomainToItemNews(it) }
    }

    private fun getLoaderItems(): List<ItemListNews> {
        return listOf(
            ItemListNews(
                tittle = "\uD83D\uDD25\uD83D\uDD25\uD83D\uDD25",
                list = IntRange(1, 3).map { ProgressItemNews },
                category = AvailableCategory.BUSINESS
            ),
            ItemListNews(
                tittle = "\uD83C\uDF0A\uD83C\uDF0A\uD83C\uDF0A",
                list = IntRange(1, 3).map { ProgressItemNews },
                category = AvailableCategory.BUSINESS
            ),
            ItemListNews(
                tittle = "\uD83D\uDC9C\uD83D\uDC9C\uD83D\uDC9C",
                list = IntRange(1, 3).map { ProgressItemNews },
                category = AvailableCategory.BUSINESS
            )
        )
    }
}