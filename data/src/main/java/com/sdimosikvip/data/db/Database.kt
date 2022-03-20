package com.sdimosikvip.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sdimosikvip.data.db.converters.AvailableCategoryConverter
import com.sdimosikvip.data.db.model.NewsCacheDB
import com.sdimosikvip.data.db.model.NewsDB

@TypeConverters(
    AvailableCategoryConverter::class,
)
@Database(
    version = 1,
    entities = [NewsDB::class, NewsCacheDB::class],
    exportSchema = true
)
abstract class Database : RoomDatabase() {
    abstract fun cacheNewsDao(): CacheNewsDao
}