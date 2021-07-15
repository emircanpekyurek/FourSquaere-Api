package com.pekyurek.emircan.presentation.core.extensions

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("setHasFixedSize")
fun setHasFixedSize(recyclerView: RecyclerView, value:Boolean) {
    recyclerView.setHasFixedSize(value)
}