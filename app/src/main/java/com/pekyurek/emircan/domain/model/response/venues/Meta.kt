package com.pekyurek.emircan.domain.model.response.venues


import com.squareup.moshi.Json

data class Meta(
    @Json(name = "code")
    val code: Int? = null,
    @Json(name = "requestId")
    val requestId: String? = null
)