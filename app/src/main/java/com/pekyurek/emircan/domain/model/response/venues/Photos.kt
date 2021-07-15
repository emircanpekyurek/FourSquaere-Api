package com.pekyurek.emircan.domain.model.response.venues


import com.squareup.moshi.Json

data class Photos(
    @Json(name = "count")
    val count: Int?,
    @Json(name = "groups")
    val groups: List<Any>?
)