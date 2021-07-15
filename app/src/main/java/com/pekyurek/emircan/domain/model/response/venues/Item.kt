package com.pekyurek.emircan.domain.model.response.venues


import com.squareup.moshi.Json

data class Item(
    @Json(name = "unreadCount")
    val unreadCount: Int?
)