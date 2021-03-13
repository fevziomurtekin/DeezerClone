package com.fevziomurtekin.deezer.di

import com.fevziomurtekin.deezer.domain.local.DeezerDao
import com.fevziomurtekin.deezer.domain.network.DeezerClient
import com.fevziomurtekin.deezer.ui.album.AlbumRepository
import com.fevziomurtekin.deezer.ui.artist.ArtistRepository
import com.fevziomurtekin.deezer.ui.favorites.FavoritesRepository
import com.fevziomurtekin.deezer.ui.main.MainRepository
import com.fevziomurtekin.deezer.ui.search.SearchRepository
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
        deezerClient: DeezerClient,
        deezerDao: DeezerDao
    ) = MainRepository(deezerClient, deezerDao)

    @Provides
    @ActivityRetainedScoped
    fun provideFavoriteRepository(
        deezerClient: DeezerClient,
        deezerDao: DeezerDao
    ) = FavoritesRepository(deezerClient, deezerDao)

    @Provides
    @ActivityRetainedScoped
    fun providesSearchRepository(
        deezerClient: DeezerClient,
        deezerDao: DeezerDao
    ) = SearchRepository(deezerClient, deezerDao)

    @Provides
    @ActivityRetainedScoped
    fun providesArtistRepository(
        deezerClient: DeezerClient
    ) = ArtistRepository(deezerClient)


    @Provides
    @ActivityRetainedScoped
    fun providesAlbumRepository(
        deezerClient: DeezerClient,
        deezerDao: DeezerDao
    ) = AlbumRepository(deezerClient,deezerDao)

}
