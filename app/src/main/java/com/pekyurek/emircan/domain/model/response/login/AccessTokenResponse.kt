package com.pekyurek.emircan.domain.model.response.login

import com.pekyurek.emircan.domain.model.base.BaseResponse
import com.squareup.moshi.Json

data class AccessTokenResponse(
    @Json(name = "access_token")
    val accessToken: String
) : BaseResponse()