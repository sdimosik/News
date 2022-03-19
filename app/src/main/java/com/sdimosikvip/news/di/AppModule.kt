package com.sdimosikvip.news.di

import android.app.Application
import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.sdimosikvip.data.network.ConnectionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    companion object {
        @Provides
        @Singleton
        fun provideGlide(@ApplicationContext context: Context): RequestManager = Glide.with(context)

        @Provides
        @Singleton
        fun provideContext(@ApplicationContext app: Application): Context = app.applicationContext

        @Provides
        @Singleton
        fun provideConnectionManager(@ApplicationContext context: Context): ConnectionManager =
            ConnectionManager(context)
    }
}