package com.pekyurek.emircan.presentation.ui.home.venues.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.pekyurek.emircan.domain.model.response.venues.Venue

class VenueDiffUtil(
    private val oldList: List<Venue>,
    private val newList: List<Venue>,
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}