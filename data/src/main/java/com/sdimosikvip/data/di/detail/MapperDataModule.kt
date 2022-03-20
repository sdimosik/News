package com.sdimosikvip.data.di.detail

import com.sdimosikvip.common.mapper.BaseBidirectionalMapper
import com.sdimosikvip.common.mapper.BaseUnidirectionalMapper
import com.sdimosikvip.data.db.mapper.NewsDBMapper
import com.sdimosikvip.data.db.model.NewsDB
import com.sdimosikvip.data.network.mapper.ArticlesMapper
import com.sdimosikvip.data.network.mapper.NewsMapper
import com.sdimosikvip.data.network.model.Article
import com.sdimosikvip.data.network.model.NewsResponse
import com.sdimosikvip.domain.model.NewsDomain
import com.sdimosikvip.domain.model.OneNewsDomain
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import javax.inject.Singleton

@Module
@DisableInstallInCheck
class MapperDataModule {

    @Provides
    @Singleton
    fun provideArticlesMapper(): BaseUnidirectionalMapper<Article, OneNewsDomain> = ArticlesMapper()

    @Provides
    @Singleton
    fun provideNewsMapper(
        articlesMapper: BaseUnidirectionalMapper<Article, OneNewsDomain>
    ): BaseUnidirectionalMapper<NewsResponse, NewsDomain> = NewsMapper(articlesMapper)

    @Provides
    @Singleton
    fun provideNewsDBMapper(): BaseBidirectionalMapper<NewsDB, OneNewsDomain> = NewsDBMapper()
}