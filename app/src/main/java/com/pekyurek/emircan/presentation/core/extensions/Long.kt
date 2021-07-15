package com.pekyurek.emircan.presentation.core.extensions

import java.text.SimpleDateFormat
import java.util.*


fun Long.toVersionFormat(): String {
    return SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(this)
}