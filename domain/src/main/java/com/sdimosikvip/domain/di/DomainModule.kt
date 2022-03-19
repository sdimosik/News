package com.sdimosikvip.domain.di

import com.sdimosikvip.domain.interactor.NewsInteractor
import com.sdimosikvip.domain.interactor.NewsInteractorImpl
import com.sdimosikvip.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun provideNewsInteractor(
        newsRepository: NewsRepository
    ): NewsInteractor = NewsInteractorImpl(newsRepository)
}