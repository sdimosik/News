package com.sdimosikvip.data.di.detail

import com.sdimosikvip.data.BuildConfig
import com.sdimosikvip.data.network.NewsApiService
import com.sdimosikvip.data.network.convert_factory.EnumConverterFactory
import com.sdimosikvip.data.network.interceptors.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@DisableInstallInCheck
class NetworkModule {

    @Singleton
    @Provides
    fun provideHTTPLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else HttpLoggingInterceptor.Level.NONE
        }
        return interceptor
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(
                AuthInterceptor(
                    BuildConfig.PARAMETR_API_TOKET_NEWS,
                    BuildConfig.API_TOKEN_NEWS
                )
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideNewsApiService(okHttpClient: OkHttpClient): NewsApiService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_NEWS)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(EnumConverterFactory())
            .client(okHttpClient)
            .build()
            .create(NewsApiService::class.java)
    }
}