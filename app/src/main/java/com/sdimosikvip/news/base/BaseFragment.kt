package com.sdimosikvip.news.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.core.content.ContentProviderCompat
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import es.dmoral.toasty.Toasty

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

    protected fun showError(msg: String) {
        Toasty.error(requireContext(), msg, Toast.LENGTH_SHORT, true).show()
    }
}