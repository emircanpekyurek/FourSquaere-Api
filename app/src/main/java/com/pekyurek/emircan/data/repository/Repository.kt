package com.pekyurek.emircan.data.repository

import com.pekyurek.emircan.domain.model.request.login.AccessTokenRequest
import com.pekyurek.emircan.domain.model.request.venues.VenuesRequest
import com.pekyurek.emircan.domain.model.response.login.AccessTokenResponse
import com.pekyurek.emircan.domain.model.response.venues.VenuesResponse
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun accessToken(accessTokenRequest: AccessTokenRequest): Flow<ResultStatus<AccessTokenResponse>>

    suspend fun venues(venuesRequest: VenuesRequest): Flow<ResultStatus<VenuesResponse>>
}