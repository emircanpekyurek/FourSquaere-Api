package com.pekyurek.emircan.domain.model.response.venues

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    @Json(name = "icon")
    val icon: Icon?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "pluralName")
    val pluralName: String?,
    @Json(name = "primary")
    val primary: Boolean?,
    @Json(name = "shortName")
    val shortName: String?
) : Parcelable