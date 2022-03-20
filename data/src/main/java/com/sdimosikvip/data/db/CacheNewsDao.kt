package com.sdimosikvip.data.db

import androidx.room.*
import com.sdimosikvip.common.model.AvailableCategory
import com.sdimosikvip.data.db.model.NewsCacheDB
import com.sdimosikvip.data.db.model.NewsDB

@Dao
interface CacheNewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNews(newsDB: NewsDB): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addInCacheTable(newsCacheDB: NewsCacheDB): Long

    @Transaction
    suspend fun cacheNews(newsDB: NewsDB, category: AvailableCategory) {
        val idNews = saveNews(newsDB)
        addInCacheTable(NewsCacheDB(idNews, category))
    }

    @Query("SELECT * FROM ${NewsCacheDB.TABLE_NAME} WHERE category = :category")
    suspend fun getCacheNewsByCategory(category: AvailableCategory): List<NewsCacheDB>

    @Query("SELECT * FROM ${NewsDB.TABLE_NAME} WHERE id = :newsId")
    suspend fun getNewsById(newsId: Long): NewsDB

    @Transaction
    suspend fun getNewsByCategory(category: AvailableCategory): List<NewsDB> {
        return getCacheNewsByCategory(category)
            .map {
                getNewsById(it.idNews)
            }
    }

    @Query(
        "DELETE FROM ${NewsDB.TABLE_NAME} WHERE id NOT IN " +
                "(SELECT id_news FROM ${NewsCacheDB.TABLE_NAME})"
    )
    suspend fun deleteManual()
}