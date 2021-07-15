package com.pekyurek.emircan.presentation.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.pekyurek.emircan.R
import com.pekyurek.emircan.databinding.CustomViewRowInfoBinding


class InfoRowView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyleAttr, defStyle) {


    val binding: CustomViewRowInfoBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.custom_view_row_info,
        this,
        true
    )

    init {
        context.obtainStyledAttributes(attrs, R.styleable.InfoRowView, defStyleAttr, defStyle).run {
            binding.tvInfoRowTitle.text = getString(R.styleable.InfoRowView_title)
            binding.tvInfoRowDescription.text = getString(R.styleable.InfoRowView_description)
            recycle()
        }
    }

    fun setTitle(text: String?) {
        binding.tvInfoRowTitle.text = text ?: ""
    }

    fun setDescription(text: String?) {
        isVisible = text.isNullOrBlank().not()
        binding.tvInfoRowDescription.text = text ?: ""
    }
}