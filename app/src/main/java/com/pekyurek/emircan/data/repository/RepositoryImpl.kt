package com.pekyurek.emircan.data.repository

import com.pekyurek.emircan.data.repository.locale.LocaleDataSource
import com.pekyurek.emircan.domain.model.request.login.AccessTokenRequest
import com.pekyurek.emircan.domain.model.request.venues.VenuesRequest
import com.pekyurek.emircan.domain.model.response.login.AccessTokenResponse
import com.pekyurek.emircan.domain.model.response.venues.VenuesResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localeDataSource: LocaleDataSource,
    private val remoteDataSource: RemoteDataSource
) : Repository {

    override suspend fun accessToken(accessTokenRequest: AccessTokenRequest): Flow<ResultStatus<AccessTokenResponse>> {
        return remoteDataSource.accessToken(accessTokenRequest)
    }

    override suspend fun venues(venuesRequest: VenuesRequest): Flow<ResultStatus<VenuesResponse>> {
        return remoteDataSource.venues(venuesRequest)
    }

}