package com.pekyurek.emircan.presentation.ui.main

import android.net.Uri
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pekyurek.emircan.data.db.AppDatabase
import com.pekyurek.emircan.data.db.dao.UserInfoDao
import com.pekyurek.emircan.data.db.model.UserInfo
import com.pekyurek.emircan.domain.constants.FoursquareLoginConstants
import com.pekyurek.emircan.domain.constants.FoursquareLoginConstants.PARAM_CODE
import com.pekyurek.emircan.domain.constants.FoursquareLoginConstants.PARAM_ERROR
import com.pekyurek.emircan.domain.constants.FoursquareLoginConstants.VALUE_ACCESS_DENIED
import com.pekyurek.emircan.domain.usecase.login.AccessTokenUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.IOException
import javax.inject.Inject


@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
internal class MainViewModelTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var accessTokenUseCase: AccessTokenUseCase

    @Inject
    lateinit var appDatabase: AppDatabase

    @Inject
    lateinit var userInfoDao: UserInfoDao

    @Before
    fun setUp() {
        hiltRule.inject()
        mainViewModel = MainViewModel(accessTokenUseCase, userInfoDao)
    }

    @Test
    fun isRedirectUrl() {
        val redirectUrl = "https://www.google.com"
        val httpRedirectUrl = "http://www.google.com"
        assert(mainViewModel.isRedirectUrl(redirectUrl))
        assert(mainViewModel.isRedirectUrl(httpRedirectUrl).not())
        assert(mainViewModel.isRedirectUrl("$redirectUrl/"))
        assert(mainViewModel.isRedirectUrl("$redirectUrl/ssss"))
    }

    @Test
    fun getCodeValue() {
        val url = "http://test.com"
        assert(mainViewModel.getCodeValue(Uri.parse("$url?$PARAM_CODE=1234")) == "1234")
        assert(mainViewModel.getCodeValue(Uri.parse("$url?query1=23&$PARAM_CODE=34")) == "34")
        assert(mainViewModel.getCodeValue(Uri.parse("$url?query1=23&$PARAM_CODE=431&query2=1253")) == "431")
        assert(mainViewModel.getCodeValue(Uri.parse("$url?$PARAM_CODE=1234#dwygd62")) == "1234")
        assert(mainViewModel.getCodeValue(Uri.parse("$url?query1=23&$PARAM_CODE=34#dwe234")) == "34")
        assert(mainViewModel.getCodeValue(Uri.parse("$url?query1=23&$PARAM_CODE=431#_=_23wes&query2=1253")) == "431")
        assert(mainViewModel.getCodeValue(Uri.parse("${url}$PARAM_CODE=1234")) == null)
        assert(mainViewModel.getCodeValue(Uri.parse("${url}query1=23&$PARAM_CODE=34")) == null)
        assert(mainViewModel.getCodeValue(Uri.parse("${url}query1=23&$PARAM_CODE=431&query2=1253")) == null)
    }

    @Test
    fun getErrorValue() {
        val url = "http://test.com"
        assert(mainViewModel.getErrorValue(Uri.parse("$url?$PARAM_ERROR=$VALUE_ACCESS_DENIED")) == VALUE_ACCESS_DENIED)
        assert(mainViewModel.getErrorValue(Uri.parse("$url?query1=23&$PARAM_ERROR=$VALUE_ACCESS_DENIED")) == VALUE_ACCESS_DENIED)
        assert(mainViewModel.getErrorValue(Uri.parse("$url?query1=23&$PARAM_ERROR=$VALUE_ACCESS_DENIED&query2=1253")) == VALUE_ACCESS_DENIED)
        assert(mainViewModel.getErrorValue(Uri.parse("$url?$PARAM_ERROR=$VALUE_ACCESS_DENIED#dwygd62")) == VALUE_ACCESS_DENIED)
        assert(mainViewModel.getErrorValue(Uri.parse("$url?query1=23&$PARAM_ERROR=$VALUE_ACCESS_DENIED#dwe234")) == VALUE_ACCESS_DENIED)
        assert(mainViewModel.getErrorValue(Uri.parse("$url?query1=23&$PARAM_ERROR=$VALUE_ACCESS_DENIED#_=_23wes&query2=1253")) == VALUE_ACCESS_DENIED)
        assert(mainViewModel.getErrorValue(Uri.parse("${url}$PARAM_ERROR=$VALUE_ACCESS_DENIED")) == null)
        assert(mainViewModel.getErrorValue(Uri.parse("${url}query1=23&$PARAM_ERROR=$VALUE_ACCESS_DENIED")) == null)
        assert(mainViewModel.getErrorValue(Uri.parse("${url}query1=23&$PARAM_ERROR=$VALUE_ACCESS_DENIED&query2=1253")) == null)
    }

    @Test
    fun errorParse() {
        val accessDenied = "access_denied"
        mainViewModel.errorParse("123")
        assert(mainViewModel.loadWebViewUrl.value == null)

        mainViewModel.errorParse(accessDenied)
        assert(mainViewModel.loadWebViewUrl.value == FoursquareLoginConstants.LOGIN_PAGE_URL)
    }

    @Test
    fun successLogin() {
        runBlocking {
            //Given
            userInfoDao.deleteLoggedInUser()
            assert(userInfoDao.getLoggedInUser() == null)
            val token = "1234"

            //When
            mainViewModel.successLogin(token).join()

            //Then
            assert(userInfoDao.getLoggedInUser() == UserInfo(token = token))
            assert(mainViewModel.successLogin.value == true)
        }
    }

    @Test
    fun `checkForAutoLogin() auto login case`() {
        runBlocking {
            //Given
            val lastLoggedInUser = UserInfo(1, "1234")
            userInfoDao.insert(lastLoggedInUser)
            assert(userInfoDao.getLoggedInUser() == lastLoggedInUser)

            //When
            mainViewModel.checkForAutoLogin().join()

            //Then
            assert(mainViewModel.successLogin.value == true)
            assert(mainViewModel.loadWebViewUrl.value == null)
        }
    }


    @Test
    fun `checkForAutoLogin() new login case`() {
        runBlocking {
            //Given
            userInfoDao.deleteLoggedInUser()
            assert(userInfoDao.getLoggedInUser() == null)

            //When
            mainViewModel.checkForAutoLogin().join()

            //Then
            assert(mainViewModel.successLogin.value == null)
            assert(mainViewModel.loadWebViewUrl.value == FoursquareLoginConstants.LOGIN_PAGE_URL)
        }
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        appDatabase.close()
    }


}