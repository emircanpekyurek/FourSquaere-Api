package com.pekyurek.emircan.domain.constants

object FoursquareLoginConstants {
    const val REDIRECT_URL = "https://www.google.com"
    const val CLIENT_ID = ""
    const val CLIENT_SECRET = ""
    const val AUTHORIZATION_CODE = "authorization_code"

    const val PARAM_CODE = "code"
    const val PARAM_ERROR = "error"

    const val VALUE_ACCESS_DENIED = "access_denied"

    const val LOGIN_PAGE_URL = "https://foursquare.com/oauth2/authorize?redirect_uri=$REDIRECT_URL&response_type=$PARAM_CODE&client_id=$CLIENT_ID"

}