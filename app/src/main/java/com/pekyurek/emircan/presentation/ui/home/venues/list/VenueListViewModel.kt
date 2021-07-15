package com.pekyurek.emircan.presentation.ui.home.venues.list

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pekyurek.emircan.data.db.dao.UserInfoDao
import com.pekyurek.emircan.domain.model.request.venues.VenuesRequest
import com.pekyurek.emircan.domain.model.response.venues.Venue
import com.pekyurek.emircan.domain.model.response.venues.VenuesResponse
import com.pekyurek.emircan.domain.usecase.venues.VenuesUseCase
import com.pekyurek.emircan.presentation.core.extensions.toVersionFormat
import com.pekyurek.emircan.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VenueListViewModel @Inject constructor(
    private val venuesUseCase: VenuesUseCase,
    private val userInfoDao: UserInfoDao,
) : BaseViewModel() {

    val logout = MutableLiveData<Boolean>()
    val venueList = MutableLiveData<List<Venue>>()

    init {
        getVenues()
    }

    private fun getVenues() = viewModelScope.launch(Dispatchers.IO) {
        val token = userInfoDao.getLoggedInUser()?.token ?: return@launch
        val bostanciLatitudeLongitude = "40.961964, 29.110559"
        request(
            flow = venuesUseCase.execute(VenuesRequest(
                oauthToken = token,
                version = System.currentTimeMillis().toVersionFormat(),
                latitudeLongitude = bostanciLatitudeLongitude,
                radius = 250
            )),
            onSuccess = { postVenues(it) }
        )
    }

    @VisibleForTesting
    fun postVenues(responseData: VenuesResponse) {
        val venues = responseData.response?.groups?.firstOrNull()?.items?.map { it.venue }
        val notNullVenues = listOfNotNull(*venues?.toTypedArray() ?: emptyArray())
        venueList.postValue(notNullVenues)
    }


    fun logout() = viewModelScope.launch(Dispatchers.IO) {
        userInfoDao.deleteLoggedInUser()
        logout.postValue(true)
    }
}