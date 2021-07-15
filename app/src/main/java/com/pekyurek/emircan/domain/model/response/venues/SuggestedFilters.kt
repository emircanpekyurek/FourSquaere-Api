package com.pekyurek.emircan.domain.model.response.venues


import com.squareup.moshi.Json

data class SuggestedFilters(
    @Json(name = "filters")
    val filters: List<Filter>?,
    @Json(name = "header")
    val header: String?
)