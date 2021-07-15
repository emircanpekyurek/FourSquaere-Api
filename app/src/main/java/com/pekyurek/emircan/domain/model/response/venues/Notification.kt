package com.pekyurek.emircan.domain.model.response.venues


import com.squareup.moshi.Json

data class Notification(
    @Json(name = "item")
    val item: Item?,
    @Json(name = "type")
    val type: String?
)