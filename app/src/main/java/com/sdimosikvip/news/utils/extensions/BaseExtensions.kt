package com.sdimosikvip.news.utils.extensions

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.transition.Fade
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.airbnb.lottie.LottieAnimationView

fun Context.browse(url: String) {
    try {
        var webpage = Uri.parse(url)
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            webpage = Uri.parse("http://$url")
        }
        val intent = Intent(Intent.ACTION_VIEW, webpage).apply {
            addCategory(Intent.CATEGORY_BROWSABLE)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        var webpage = Uri.parse(url)
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            webpage = Uri.parse("http://$url")
        }
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, webpage)
    }
}

fun View.fadeVisibility(visibility: Int, duration: Long = 400) {
    val transition: Transition = Fade()
    transition.duration = duration
    transition.addTarget(this)
    TransitionManager.beginDelayedTransition(this.parent as ViewGroup, transition)
    this.visibility = visibility
}

fun LottieAnimationView.fadeVisibility(visibility: Int, duration: Long = 400) {
    val transition: Transition = Fade()
    transition.duration = duration
    transition.addTarget(this)
    TransitionManager.beginDelayedTransition(this.parent as ViewGroup, transition)
    this.visibility = visibility
    when (visibility) {
        View.VISIBLE -> this.playAnimation()
        else -> this.pauseAnimation()
    }
}