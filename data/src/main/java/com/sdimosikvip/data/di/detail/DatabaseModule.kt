package com.sdimosikvip.data.di.detail

import android.content.Context
import androidx.room.Room
import com.sdimosikvip.data.db.CacheNewsDao
import com.sdimosikvip.data.db.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import javax.inject.Singleton

@Module
@DisableInstallInCheck
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(context: Context): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            "Cache.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideFavouriteStockDAO(database: Database): CacheNewsDao {
        return database.cacheNewsDao()
    }
}