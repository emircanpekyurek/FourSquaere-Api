package com.pekyurek.emircan.presentation.core.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

fun ViewGroup.inflate(@LayoutRes layoutResId: Int, attachToParent: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutResId, this, attachToParent)
}

fun <T : ViewDataBinding> ViewGroup.inflateDataBinding(
    @LayoutRes layoutResId: Int,
    attachToParent: Boolean = false,
): T {
    return DataBindingUtil.inflate(LayoutInflater.from(context),
        layoutResId,
        this,
        attachToParent)

}