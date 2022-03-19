package com.sdimosikvip.news.app

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.sdimosikvip.news.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}