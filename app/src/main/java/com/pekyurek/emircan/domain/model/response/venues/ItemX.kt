package com.pekyurek.emircan.domain.model.response.venues


import com.squareup.moshi.Json

data class ItemX(
    @Json(name = "reasons")
    val reasons: Reasons? = null,
    @Json(name = "referralId")
    val referralId: String? = null,
    @Json(name = "venue")
    val venue: Venue?
)