package com.pekyurek.emircan.data.db.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pekyurek.emircan.data.db.AppDatabase
import com.pekyurek.emircan.data.db.model.UserInfo
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
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
internal class UserInfoDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var userInfoDao: UserInfoDao

    @Inject
    lateinit var appDatabase: AppDatabase

    private val loggedInUser = UserInfo(1,"token123")

    @Before
    fun setUp() {
        hiltRule.inject()
        userInfoDao.insert(loggedInUser)
    }

    @Test
    fun deleteLoggedInUser() {
        //When
        userInfoDao.deleteLoggedInUser()

        //Then
        assert(userInfoDao.getLoggedInUser() == null)
    }

    @Test
    fun getLoggedInUser() {
        //When
        val result = userInfoDao.getLoggedInUser()

        //Then
        assert(result == loggedInUser)
    }

    @Test
    fun insert() {
        //Given
        userInfoDao.deleteLoggedInUser()
        val newUser = UserInfo(2, "token1")

        //When
        userInfoDao.insert(newUser)

        //Then
        assert(userInfoDao.getLoggedInUser() == newUser)
    }


    @After
    @Throws(IOException::class)
    fun closeDb() {
        appDatabase.close()
    }

}