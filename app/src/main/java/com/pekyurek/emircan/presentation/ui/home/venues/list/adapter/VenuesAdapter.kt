package com.pekyurek.emircan.presentation.ui.home.venues.list.adapter

import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pekyurek.emircan.R
import com.pekyurek.emircan.databinding.ItemVenuesBinding
import com.pekyurek.emircan.domain.model.response.venues.Venue
import com.pekyurek.emircan.presentation.core.extensions.inflateDataBinding

class VenuesAdapter(
    private val onItemClick: (navigationExtra: FragmentNavigator.Extras, venue: Venue) -> Unit,
) : RecyclerView.Adapter<VenuesAdapter.ViewHolder>() {

    val list = mutableListOf<Venue>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflateDataBinding(R.layout.item_venues))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(newList: List<Venue>) {
        val diffCallback = VenueDiffUtil(list, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemVenuesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(venue: Venue) {
            binding.venue = venue
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                onItemClick.invoke(
                    FragmentNavigatorExtras(
                        binding.ivVenueIcon to binding.ivVenueIcon.transitionName,
                        binding.tvVenueName to binding.tvVenueName.transitionName,
                        binding.tvVenueAddress to binding.tvVenueAddress.transitionName
                    ),
                    venue
                )
            }
        }
    }
}
