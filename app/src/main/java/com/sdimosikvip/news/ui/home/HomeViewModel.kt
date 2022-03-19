package com.sdimosikvip.news.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sdimosikvip.domain.interactor.NewsInteractor
import com.sdimosikvip.news.base.BaseViewModel
import com.sdimosikvip.news.mapper.newsDomainToItemNews
import com.sdimosikvip.news.model.ItemListNews
import com.sdimosikvip.news.model.ProgressItemNews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsInteractor: NewsInteractor
) : BaseViewModel() {

    private val _list = MutableLiveData<List<ItemListNews>>()
    val list: LiveData<List<ItemListNews>> = _list

    init {
        viewModelScope.launch() {
            withContext(Dispatchers.IO) {
                _list.postValue(getLoaderItems())
                val items = getItems()
                _list.postValue(items)
            }
        }
    }

    private suspend fun getItems(): List<ItemListNews> {
        val result = newsInteractor.getAllHotNews()
        return result.map { newsDomainToItemNews(it) }
    }

    private suspend fun getLoaderItems(): List<ItemListNews> {
        return listOf(
            ItemListNews(
                tittle = "Tittle 1",
                list = IntRange(1, 3).map { ProgressItemNews }
            ),
            ItemListNews(
                tittle = "Tittle 2",
                list = IntRange(1, 3).map { ProgressItemNews }
            ),
            ItemListNews(
                tittle = "Tittle 3",
                list = IntRange(1, 3).map { ProgressItemNews }
            )
        )
    }
}