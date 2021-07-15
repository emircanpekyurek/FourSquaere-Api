package com.pekyurek.emircan.domain.model.response.venues


import com.squareup.moshi.Json

data class Group(
    @Json(name = "items")
    val items: List<ItemX>? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "type")
    val type: String? = null
)