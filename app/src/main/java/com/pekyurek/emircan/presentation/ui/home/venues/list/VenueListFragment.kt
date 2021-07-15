package com.pekyurek.emircan.presentation.ui.home.venues.list

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pekyurek.emircan.R
import com.pekyurek.emircan.databinding.FragmentVenueListBinding
import com.pekyurek.emircan.presentation.ui.base.BaseFragment
import com.pekyurek.emircan.presentation.ui.home.venues.list.adapter.VenuesAdapter
import com.pekyurek.emircan.presentation.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VenueListFragment : BaseFragment<FragmentVenueListBinding, VenueListViewModel>() {

    override val layoutResId: Int = R.layout.fragment_venue_list

    override val viewModel: VenueListViewModel by viewModels()

    private val venuesAdapter by lazy {
        VenuesAdapter { navigationExtra, venue ->
            val action =
                VenueListFragmentDirections.actionVenueListToVenueDetailFragment(venue)
            findNavController().navigate(action, navigationExtra)
        }
    }

    override fun onInit() {
        initAnimations()
    }

    private fun initAnimations() {
        postponeEnterTransition()
        binding.rvVenues.viewTreeObserver.addOnPreDrawListener {
            startPostponedEnterTransition()
            true
        }
    }

    override fun initBinding() {
        binding.viewModel = viewModel
    }

    override fun initViews() {
        binding.rvVenues.adapter  = venuesAdapter
    }

    override fun setObservers() {
        super.setObservers()
        viewModel.venueList.observe(this) {
            venuesAdapter.setData(it)
        }

        viewModel.logout.observe(this) {
            activity?.run {
                finish()
                startActivity(MainActivity.newIntent(this))
            }
        }
    }
}