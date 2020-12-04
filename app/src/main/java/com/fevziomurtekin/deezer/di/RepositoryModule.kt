package com.fevziomurtekin.deezer.di

import com.fevziomurtekin.deezer.domain.local.DeezerDao
import com.fevziomurtekin.deezer.domain.network.DeezerClient
import com.fevziomurtekin.deezer.repository.DeezerRepository
import com.fevziomurtekin.deezer.ui.main.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun provideDeezerRepository(
        deezerClient: DeezerClient,
        deezerDao: DeezerDao
    ): DeezerRepository = DeezerRepository(deezerClient,deezerDao)

    @Provides
    @ActivityRetainedScoped
    fun provideMainRepository(
        deezerClient: DeezerClient,
        deezerDao: DeezerDao
    ): MainRepository = MainRepository(deezerClient, deezerDao)


}