package com.sdimosikvip.data.sources.local

import com.sdimosikvip.common.model.AvailableCategory
import com.sdimosikvip.data.db.model.NewsDB

interface NewsLocalSource {

    suspend fun deleteManual()

    suspend fun getNewsByCategory(category: AvailableCategory): List<NewsDB>

    suspend fun cacheNews(newsDB: NewsDB, category: AvailableCategory)
}