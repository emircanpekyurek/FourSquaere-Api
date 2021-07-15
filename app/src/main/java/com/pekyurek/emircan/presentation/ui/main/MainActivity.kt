package com.pekyurek.emircan.presentation.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.CookieManager
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.viewModels
import com.pekyurek.emircan.R
import com.pekyurek.emircan.databinding.ActivityMainBinding
import com.pekyurek.emircan.presentation.ui.base.BaseActivity
import com.pekyurek.emircan.presentation.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val layoutResId: Int get() = R.layout.activity_main

    override val viewModel: MainViewModel by viewModels()

    override fun initBinding() {
        super.initBinding()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun initViews() {
        binding.webView.apply {
            settings.javaScriptEnabled = true
            webViewClient = codeControlWebViewClient()
        }
    }

    override fun onInit(savedInstanceState: Bundle?) {
        viewModel.checkForAutoLogin()
    }

    override fun setObservers() {
        super.setObservers()
        viewModel.successLogin.observe(this) {
            finish()
            startActivity(HomeActivity.newIntent(this))
        }

        viewModel.loadWebViewUrl.observe(this) {
            clearWebViewCookies()
            binding.webView.loadUrl(it)
        }
    }

    private fun clearWebViewCookies() {
        CookieManager.getInstance().removeAllCookies(null)
        CookieManager.getInstance().flush()
    }

    private fun codeControlWebViewClient() = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            return viewModel.loginCompletedControl(request?.url.toString())
        }
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

}