package com.sdimosikvip.data.sources.local

import com.sdimosikvip.common.model.AvailableCategory
import com.sdimosikvip.data.db.CacheNewsDao
import com.sdimosikvip.data.db.model.NewsDB
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsLocalSourceImpl @Inject constructor(
    private val cacheNewsDao: CacheNewsDao,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) : NewsLocalSource {

    override suspend fun deleteManual() =
        withContext(defaultDispatcher) {
            cacheNewsDao.deleteManual()
        }

    override suspend fun getNewsByCategory(category: AvailableCategory): List<NewsDB> =
        withContext(defaultDispatcher) {
            cacheNewsDao.getNewsByCategory(category)
        }

    override suspend fun cacheNews(newsDB: NewsDB, category: AvailableCategory) =
        withContext(defaultDispatcher) {
            cacheNewsDao.cacheNews(newsDB, category)
        }
}