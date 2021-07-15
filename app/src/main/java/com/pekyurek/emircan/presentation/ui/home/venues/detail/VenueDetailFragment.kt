package com.pekyurek.emircan.presentation.ui.home.venues.detail

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.pekyurek.emircan.R
import com.pekyurek.emircan.databinding.FragmentVenueDetailBinding
import com.pekyurek.emircan.presentation.ui.base.BaseFragment

class VenueDetailFragment : BaseFragment<FragmentVenueDetailBinding, VenueDetailViewModel>() {

    override val layoutResId: Int = R.layout.fragment_venue_detail

    private val args: VenueDetailFragmentArgs by navArgs()

    override val viewModel: VenueDetailViewModel by viewModels()

    override fun initBinding() {
        binding.venue = args.argVenue
        binding.lifecycleOwner = this
    }

    override fun initViews() {
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }
}
