package com.pekyurek.emircan.presentation.ui.main

import android.net.Uri
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pekyurek.emircan.data.db.dao.UserInfoDao
import com.pekyurek.emircan.data.db.model.UserInfo
import com.pekyurek.emircan.domain.constants.FoursquareLoginConstants
import com.pekyurek.emircan.domain.model.request.login.AccessTokenRequest
import com.pekyurek.emircan.domain.usecase.login.AccessTokenUseCase
import com.pekyurek.emircan.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val accessTokenUseCase: AccessTokenUseCase,
    private val userInfoDao: UserInfoDao
) : BaseViewModel() {

    val successLogin = MutableLiveData<Boolean>()
    val loadWebViewUrl = MutableLiveData<String>()

    fun loginCompletedControl(url: String): Boolean {
        if (isRedirectUrl(url)) {
            val uri = Uri.parse(url)
            getCodeValue(uri)?.let { code ->
                loginRequest(code)
                return true
            }
            getErrorValue(uri)?.let { error ->
                errorParse(error)
                return true
            }
        }
        return false
    }

    @VisibleForTesting
    fun isRedirectUrl(url: String): Boolean {
        return url.startsWith(FoursquareLoginConstants.REDIRECT_URL)
    }

    @VisibleForTesting
    fun getCodeValue(uri: Uri): String? {
        return uri.getQueryParameter(FoursquareLoginConstants.PARAM_CODE)?.split("#")?.firstOrNull()
    }

    private fun loginRequest(code: String) = viewModelScope.launch(Dispatchers.IO) {
        request(
            flow = accessTokenUseCase.execute(AccessTokenRequest(
                clientId = FoursquareLoginConstants.CLIENT_ID,
                clientSecret = FoursquareLoginConstants.CLIENT_SECRET,
                grantType = FoursquareLoginConstants.AUTHORIZATION_CODE,
                redirectUri = FoursquareLoginConstants.REDIRECT_URL,
                code = code
            )),
            onSuccess = { successLogin(it.accessToken) }
        )
    }

    @VisibleForTesting
    fun getErrorValue(uri: Uri): String? {
        return uri.getQueryParameter(FoursquareLoginConstants.PARAM_ERROR)?.split("#")?.firstOrNull()
    }

    @VisibleForTesting
    fun errorParse(error: String) {
        if (error == FoursquareLoginConstants.VALUE_ACCESS_DENIED) {
            loadWebViewUrl.postValue(FoursquareLoginConstants.LOGIN_PAGE_URL)
        }
    }

    @VisibleForTesting
    fun successLogin(token: String) = viewModelScope.launch(Dispatchers.IO) {
        userInfoDao.insert(UserInfo(token = token))
        successLogin.postValue(true)
    }

    fun checkForAutoLogin() = viewModelScope.launch(Dispatchers.IO) {
        userInfoDao.getLoggedInUser()?.let {
            successLogin.postValue(true)
        } ?: kotlin.run {
            loadWebViewUrl.postValue(FoursquareLoginConstants.LOGIN_PAGE_URL)
        }
    }

}