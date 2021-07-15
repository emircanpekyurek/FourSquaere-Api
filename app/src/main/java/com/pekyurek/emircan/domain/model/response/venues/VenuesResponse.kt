package com.pekyurek.emircan.domain.model.response.venues


import com.pekyurek.emircan.domain.model.base.BaseResponse
import com.squareup.moshi.Json

data class VenuesResponse(
    @Json(name = "meta")
    val meta: Meta?,
    @Json(name = "notifications")
    val notifications: List<Notification>?,
    @Json(name = "response")
    val response: Response?,
) : BaseResponse()