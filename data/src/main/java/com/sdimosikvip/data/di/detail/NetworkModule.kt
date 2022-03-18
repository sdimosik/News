package com.sdimosikvip.data.di.detail

import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@DisableInstallInCheck
class NetworkModule {

    @Singleton
    @Provides
    fun provideHTTPLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }
}