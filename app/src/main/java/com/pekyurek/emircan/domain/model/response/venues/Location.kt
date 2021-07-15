package com.pekyurek.emircan.domain.model.response.venues


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location(
    @Json(name = "address")
    val address: String?,
    @Json(name = "cc")
    val cc: String?,
    @Json(name = "city")
    val city: String?,
    @Json(name = "country")
    val country: String?,
    @Json(name = "crossStreet")
    val crossStreet: String?,
    @Json(name = "distance")
    val distance: Int?,
    @Json(name = "formattedAddress")
    val formattedAddress: List<String>?,
    @Json(name = "lat")
    val lat: Double?,
    @Json(name = "lng")
    val lng: Double?,
    @Json(name = "neighborhood")
    val neighborhood: String?,
    @Json(name = "postalCode")
    val postalCode: String?,
    @Json(name = "state")
    val state: String?
) : Parcelable