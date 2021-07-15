package com.pekyurek.emircan.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserInfo(
    @PrimaryKey
    val id: Int = 0,
    val token: String,
)