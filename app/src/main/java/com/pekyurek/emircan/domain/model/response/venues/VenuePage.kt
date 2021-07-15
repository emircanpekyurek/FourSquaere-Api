package com.pekyurek.emircan.domain.model.response.venues


import com.squareup.moshi.Json

data class VenuePage(
    @Json(name = "id")
    val id: String?
)