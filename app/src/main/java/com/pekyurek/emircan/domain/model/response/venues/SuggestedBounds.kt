package com.pekyurek.emircan.domain.model.response.venues


import com.squareup.moshi.Json

data class SuggestedBounds(
    @Json(name = "ne")
    val ne: Ne?,
    @Json(name = "sw")
    val sw: Sw?
)