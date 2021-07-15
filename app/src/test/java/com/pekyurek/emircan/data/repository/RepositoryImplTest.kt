package com.pekyurek.emircan.data.repository

import android.content.Context
import com.pekyurek.emircan.data.repository.locale.LocaleDataSource
import com.pekyurek.emircan.domain.exception.ServiceException
import com.pekyurek.emircan.domain.exception.base.BaseException
import com.pekyurek.emircan.domain.exception.service.FailResponseException
import com.pekyurek.emircan.domain.exception.service.NullResponseException
import com.pekyurek.emircan.domain.model.request.login.AccessTokenRequest
import com.pekyurek.emircan.domain.model.response.login.AccessTokenResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class RepositoryImplTest {

    private val localeDataSource = mockk<LocaleDataSource>()
    private val remoteDataSource = mockk<RemoteDataSource>()

    private lateinit var repositoryImpl: RepositoryImpl

    private val context: Context = mockk(relaxed = true)

    @Before
    fun setUp() {
        repositoryImpl = RepositoryImpl(localeDataSource, remoteDataSource)
    }

    private val requestModel = AccessTokenRequest(
        "1",
        "123",
        "type",
        "httos://www.cc.cc,",
        "124325")

    @Test
    fun `accessToken() success case`() {
        runBlocking {
            //Given
            val responseModel = AccessTokenResponse("token123")

            val responseFlow = flow {
                emit(ResultStatus.Loading(show = true))
                emit(ResultStatus.Success(responseModel))
                emit(ResultStatus.Loading(show = false))
            }

            coEvery {
                remoteDataSource.accessToken(requestModel)
            } returns responseFlow

            //When
            val result = repositoryImpl.accessToken(requestModel).toList()

            //Then
            assert(result[0] is ResultStatus.Loading)
            assert((result[0] as ResultStatus.Loading).show)
            assert((result[1] is ResultStatus.Success))
            assert((result[1] as ResultStatus.Success).data == responseModel)
            assert(result[2] is ResultStatus.Loading)
            assert((result[2] as ResultStatus.Loading).show.not())
        }
    }

    @Test
    fun `accessToken() NullResponseException case`() {
        accessTokenFailCase(NullResponseException(context))
    }

    @Test
    fun `accessToken() ServiceException case`() {
        accessTokenFailCase(ServiceException("mock error"))
    }

    @Test
    fun `accessToken() FailResponseException case`() {
        accessTokenFailCase(FailResponseException(context, 404))
    }


    private fun accessTokenFailCase(exception: BaseException) {
        runBlocking {
            //Given
            val responseFlow = flow {
                emit(ResultStatus.Loading(show = true))
                emit(ResultStatus.Exception(exception))
                emit(ResultStatus.Loading(show = false))
            }
            coEvery {
                remoteDataSource.accessToken(requestModel)
            } returns responseFlow

            //When
            val result = repositoryImpl.accessToken(requestModel).toList()

            //Then
            assert(result[0] is ResultStatus.Loading)
            assert((result[0] as ResultStatus.Loading).show)
            assert(result[1] is ResultStatus.Exception)
            assert(result[1] is ResultStatus.Exception)

            assert((result[1] as ResultStatus.Exception).exception.message == exception.message)
            assert((result[1] as ResultStatus.Exception).exception == exception)

            assert(result[2] is ResultStatus.Loading)
            assert((result[2] as ResultStatus.Loading).show.not())
        }
    }



}