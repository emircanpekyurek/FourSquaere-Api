package com.pekyurek.emircan.domain.model.response.venues


import com.squareup.moshi.Json

data class LabeledLatLng(
    @Json(name = "label")
    val label: String?,
    @Json(name = "lat")
    val lat: Double?,
    @Json(name = "lng")
    val lng: Double?
)