package com.sdimosikvip.news.app

import android.app.Application
import android.graphics.Typeface
import com.jakewharton.threetenabp.AndroidThreeTen
import com.sdimosikvip.news.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import es.dmoral.toasty.Toasty
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        Toasty.Config.getInstance()
            .setToastTypeface(
                Typeface.createFromAsset(
                    this.assets,
                    "montserrat_semi_bold.ttf"
                )
            )
            .setTextSize(14)
            .apply()
    }
}