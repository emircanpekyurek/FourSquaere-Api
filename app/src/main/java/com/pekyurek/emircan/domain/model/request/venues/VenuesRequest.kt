package com.pekyurek.emircan.domain.model.request.venues

import com.pekyurek.emircan.domain.model.base.BaseRequest

data class VenuesRequest(
    val oauthToken: String,
    val version: String,
    val latitudeLongitude: String,
    val radius: Int
) : BaseRequest()