package com.sdimosikvip.news.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment(
    @LayoutRes layoutId: Int
) : Fragment(layoutId) {

    protected abstract val binding: ViewBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        subscribe()
    }

    protected open fun setupViews() {}

    protected open fun subscribe() {}

}