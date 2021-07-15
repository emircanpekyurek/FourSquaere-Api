package com.pekyurek.emircan.presentation.ui.home.venues.list


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pekyurek.emircan.data.db.AppDatabase
import com.pekyurek.emircan.data.db.dao.UserInfoDao
import com.pekyurek.emircan.domain.model.response.venues.*
import com.pekyurek.emircan.domain.usecase.venues.VenuesUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.delay
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
internal class VenueListViewModelTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var venuesUseCase: VenuesUseCase

    @Inject
    lateinit var userInfoDao: UserInfoDao

    @Inject
    lateinit var appDatabase: AppDatabase

    lateinit var venueListViewModel: VenueListViewModel

    @Before
    fun setUp() {
        hiltRule.inject()
        venueListViewModel = VenueListViewModel(venuesUseCase, userInfoDao)
    }

    @Test
    fun postVenues() {
        runBlocking {
            //Given
            val venue1 = Venue(id = "1")
            val venue3 = Venue(id = "3")

            val item1 = ItemX(venue = venue1)
            val item2 = ItemX(venue = null)
            val item3 = ItemX(venue = venue3)

            val response = VenuesResponse(Meta(),
                emptyList(),
                Response(groups = listOf(Group(items = listOf(item1, item2, item3)))))

            //When
            venueListViewModel.postVenues(response)
            val result = venueListViewModel.venueList.value

            //Then
            assert(result == listOf(venue1, venue3))
        }
    }


    @After
    @Throws(IOException::class)
    fun closeDb() {
        appDatabase.close()
    }
}