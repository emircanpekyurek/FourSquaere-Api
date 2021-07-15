package com.pekyurek.emircan.di

import com.pekyurek.emircan.data.repository.Repository
import com.pekyurek.emircan.domain.usecase.login.AccessTokenUseCase
import com.pekyurek.emircan.domain.usecase.venues.VenuesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object UseCaseModule {

    @Provides
    fun provideAccessTokenUseCase(repository: Repository) = AccessTokenUseCase(repository)

    @Provides
    fun provideVenuesUseCase(repository: Repository) = VenuesUseCase(repository)

}