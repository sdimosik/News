package com.sdimosikvip.data.di

import com.sdimosikvip.common.mapper.BaseBidirectionalMapper
import com.sdimosikvip.common.mapper.BaseUnidirectionalMapper
import com.sdimosikvip.data.db.CacheNewsDao
import com.sdimosikvip.data.db.mapper.NewsDBMapper
import com.sdimosikvip.data.db.model.NewsDB
import com.sdimosikvip.data.di.detail.DatabaseModule
import com.sdimosikvip.data.di.detail.MapperDataModule
import com.sdimosikvip.data.di.detail.NetworkModule
import com.sdimosikvip.data.network.ConnectionManager
import com.sdimosikvip.data.network.NewsApiService
import com.sdimosikvip.data.network.model.NewsResponse
import com.sdimosikvip.data.repository.NewsRepositoryImpl
import com.sdimosikvip.data.sources.local.NewsLocalSource
import com.sdimosikvip.data.sources.local.NewsLocalSourceImpl
import com.sdimosikvip.data.sources.remote.NewsRemoteSource
import com.sdimosikvip.data.sources.remote.NewsRemoteSourceImpl
import com.sdimosikvip.domain.model.NewsDomain
import com.sdimosikvip.domain.model.OneNewsDomain
import com.sdimosikvip.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(
    includes = [
        DatabaseModule::class,
        NetworkModule::class,
        MapperDataModule::class
    ]
)
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideNewsLocalSource(
        cacheNewsDao: CacheNewsDao
    ): NewsLocalSource = NewsLocalSourceImpl(cacheNewsDao)

    @Provides
    @Singleton
    fun provideNewsRemoteSource(
        newsApiService: NewsApiService,
        newsMapper: BaseUnidirectionalMapper<NewsResponse, NewsDomain>,
        connectionManager: ConnectionManager,
    ): NewsRemoteSource = NewsRemoteSourceImpl(
        newsApiService, newsMapper, connectionManager
    )

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsDBMapper: BaseBidirectionalMapper<NewsDB, OneNewsDomain>,
        newsRemoteSource: NewsRemoteSource,
        newsLocalSource: NewsLocalSource,
        connectionManager: ConnectionManager
    ): NewsRepository =
        NewsRepositoryImpl(newsDBMapper, newsRemoteSource, newsLocalSource, connectionManager)
}