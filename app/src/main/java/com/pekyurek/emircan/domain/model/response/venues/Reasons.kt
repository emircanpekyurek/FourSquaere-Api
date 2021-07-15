package com.pekyurek.emircan.domain.model.response.venues


import com.squareup.moshi.Json

data class Reasons(
    @Json(name = "count")
    val count: Int?,
    @Json(name = "items")
    val items: List<ItemXX>?
)