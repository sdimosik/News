package com.sdimosikvip.data.di

import com.sdimosikvip.data.di.detail.DatabaseModule
import com.sdimosikvip.data.di.detail.NetworkModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        DatabaseModule::class,
        NetworkModule::class
    ]
)
@InstallIn(SingletonComponent::class)
class DataModule {

}