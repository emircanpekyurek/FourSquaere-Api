package com.pekyurek.emircan.domain.model.response.venues

import com.squareup.moshi.Json


data class Filter(
    @Json(name = "key")
    val key: String?,
    @Json(name = "name")
    val name: String?
)