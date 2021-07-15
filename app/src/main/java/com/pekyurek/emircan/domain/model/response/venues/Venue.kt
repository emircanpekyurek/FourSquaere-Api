package com.pekyurek.emircan.domain.model.response.venues


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Venue(
    @Json(name = "categories")
    val categories: List<Category>? = null,
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "location")
    val location: Location? = null,
    @Json(name = "name")
    val name: String? = null,
    //Not Json
    val imageTransitionId: String = "venue_image_transition_$id",
    val nameTransitionId: String = "venue_name_transition_$id",
    val addressTransitionId: String = "venue_address_transition_$id"
) : Parcelable