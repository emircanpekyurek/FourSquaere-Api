package com.pekyurek.emircan.domain.model.response.venues


import com.squareup.moshi.Json

data class ItemXX(
    @Json(name = "reasonName")
    val reasonName: String?,
    @Json(name = "summary")
    val summary: String?,
    @Json(name = "type")
    val type: String?
)