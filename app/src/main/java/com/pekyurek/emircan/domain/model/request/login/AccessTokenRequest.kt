package com.pekyurek.emircan.domain.model.request.login

import com.pekyurek.emircan.domain.model.base.BaseRequest

data class AccessTokenRequest(
    val clientId: String,
    val clientSecret: String,
    val grantType: String,
    val redirectUri: String,
    val code: String,
) : BaseRequest()