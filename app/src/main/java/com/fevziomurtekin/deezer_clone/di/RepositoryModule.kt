package com.fevziomurtekin.deezer_clone.di

import com.fevziomurtekin.deezer_clone.domain.local.DeezerDao
import com.fevziomurtekin.deezer_clone.domain.network.DeezerService
import com.fevziomurtekin.deezer_clone.repository.MainRepository
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
    fun provideMainRepository(
        deezerService: DeezerService,
        deezerDao: DeezerDao
    ): MainRepository = MainRepository(deezerService,deezerDao)


}