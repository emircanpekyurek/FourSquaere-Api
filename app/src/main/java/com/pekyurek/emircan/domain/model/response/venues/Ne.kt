package com.pekyurek.emircan.domain.model.response.venues


import com.squareup.moshi.Json

data class Ne(
    @Json(name = "lat")
    val lat: Double?,
    @Json(name = "lng")
    val lng: Double?
)