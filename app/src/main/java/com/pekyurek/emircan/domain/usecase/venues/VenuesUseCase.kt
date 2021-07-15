package com.pekyurek.emircan.domain.usecase.venues

import com.pekyurek.emircan.data.repository.Repository
import com.pekyurek.emircan.data.repository.ResultStatus
import com.pekyurek.emircan.domain.model.request.venues.VenuesRequest
import com.pekyurek.emircan.domain.model.response.venues.VenuesResponse
import com.pekyurek.emircan.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VenuesUseCase @Inject constructor(private val repository: Repository) :
    UseCase<VenuesRequest, VenuesResponse> {

    override suspend fun execute(request: VenuesRequest): Flow<ResultStatus<VenuesResponse>> {
        return repository.venues(request)
    }

}