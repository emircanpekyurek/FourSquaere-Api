package com.pekyurek.emircan.domain.model.response.venues


import com.squareup.moshi.Json

data class Response(
    @Json(name = "groups")
    val groups: List<Group>? = null,
    @Json(name = "headerFullLocation")
    val headerFullLocation: String? = null,
    @Json(name = "headerLocation")
    val headerLocation: String? = null,
    @Json(name = "headerLocationGranularity")
    val headerLocationGranularity: String? = null,
    @Json(name = "suggestedBounds")
    val suggestedBounds: SuggestedBounds? = null,
    @Json(name = "suggestedFilters")
    val suggestedFilters: SuggestedFilters? = null,
    @Json(name = "totalResults")
    val totalResults: Int? = null
)