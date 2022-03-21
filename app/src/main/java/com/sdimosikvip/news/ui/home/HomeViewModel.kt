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

    private suspend fun getItems(): List<ItemListNews> {
        val result = newsInteractor.getAllHotNews()
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