package com.pekyurek.emircan.presentation.ui.home

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import com.pekyurek.emircan.R
import com.pekyurek.emircan.databinding.ActivityHomeBinding
import com.pekyurek.emircan.presentation.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {

    override val layoutResId: Int = R.layout.activity_home

    override val viewModel: HomeViewModel by viewModels()

    companion object {
        fun newIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }

}