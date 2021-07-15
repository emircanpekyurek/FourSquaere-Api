package com.pekyurek.emircan.domain.usecase.login

import com.pekyurek.emircan.data.repository.Repository
import com.pekyurek.emircan.data.repository.ResultStatus
import com.pekyurek.emircan.domain.model.request.login.AccessTokenRequest
import com.pekyurek.emircan.domain.model.response.login.AccessTokenResponse
import com.pekyurek.emircan.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccessTokenUseCase @Inject constructor(private val repository: Repository) :
    UseCase<AccessTokenRequest, AccessTokenResponse> {

    override suspend fun execute(request: AccessTokenRequest): Flow<ResultStatus<AccessTokenResponse>> {
        return repository.accessToken(request)
    }
}