package com.sdimosikvip.news.di

import com.sdimosikvip.domain.interactor.NewsInteractor
import com.sdimosikvip.news.ui.home.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class ViewModelsModule {

    @Provides
    fun provideHomeViewModel(
        newsInteractor: NewsInteractor
    ) = HomeViewModel(newsInteractor)
}