package com.sdimosikvip.news.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sdimosikvip.news.base.BaseViewModel
import com.sdimosikvip.news.model.ItemListNews
import com.sdimosikvip.news.model.ItemNews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : BaseViewModel() {

    private val _list = MutableLiveData<List<ItemListNews>>()
    val list: LiveData<List<ItemListNews>> = _list

    init {
        viewModelScope.launch() {
            withContext(Dispatchers.IO) {
                val items = getItems()
                _list.postValue(items)
            }
        }
    }

    private suspend fun getItems(): List<ItemListNews> {
        return listOf(
            ItemListNews(
                listOf(
                    ItemNews(
                        "1",
                        "",
                        "Some news text. Some text he he"
                    ),
                    ItemNews(
                        "2",
                        "",
                        "Some news text. Some text he he"
                    ),
                    ItemNews(
                        "3",
                        "",
                        "Some news text. Some text he he"
                    ),
                    ItemNews(
                        "4",
                        "",
                        "Some news text. Some text he he"
                    ),
                    ItemNews(
                        "5",
                        "",
                        "Some news text. Some text he he"
                    )
                ),
                "Category 1"
            ),
            ItemListNews(
                listOf(
                    ItemNews(
                        "1",
                        "",
                        "Some news text. Some text he he"
                    ),
                    ItemNews(
                        "2",
                        "",
                        "Some news text. Some text he he"
                    ),
                    ItemNews(
                        "3",
                        "",
                        "Some news text. Some text he he"
                    ),
                    ItemNews(
                        "4",
                        "",
                        "Some news text. Some text he he"
                    ),
                    ItemNews(
                        "5",
                        "",
                        "Some news text. Some text he he"
                    )
                ),
                "Category 2"
            ),
            ItemListNews(
                listOf(
                    ItemNews(
                        "1",
                        "",
                        "Some news text. Some text he he"
                    ),
                    ItemNews(
                        "2",
                        "",
                        "Some news text. Some text he he"
                    ),
                    ItemNews(
                        "3",
                        "",
                        "Some news text. Some text he he"
                    ),
                    ItemNews(
                        "4",
                        "",
                        "Some news text. Some text he he"
                    ),
                    ItemNews(
                        "5",
                        "",
                        "Some news text. Some text he he"
                    )
                ),
                "Category 3"
            ),
            ItemListNews(
                listOf(
                    ItemNews(
                        "1",
                        "",
                        "Some news text. Some text he he"
                    ),
                    ItemNews(
                        "2",
                        "",
                        "Some news text. Some text he he"
                    ),
                    ItemNews(
                        "3",
                        "",
                        "Some news text. Some text he he"
                    ),
                    ItemNews(
                        "4",
                        "",
                        "Some news text. Some text he he"
                    ),
                    ItemNews(
                        "5",
                        "",
                        "Some news text. Some text he he"
                    )
                ),
                "Category 4"
            )
        )
    }
}