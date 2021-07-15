package com.pekyurek.emircan.data.repository

import com.pekyurek.emircan.domain.model.response.login.AccessTokenResponse
import com.pekyurek.emircan.domain.model.response.venues.VenuesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("oauth2/access_token")
    suspend fun accessToken(
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("grant_type") grantType: String,
        @Query("redirect_uri") redirectUri: String,
        @Query("code") code: String,
    ): Response<AccessTokenResponse>

    @GET("v2/venues/explore")
    suspend fun venues(
        @Query("oauth_token") token: String,
        @Query("v") version: String,
        @Query("ll") latitudeLongitude: String,
        @Query("radius") radius: Int
    ): Response<VenuesResponse>


}