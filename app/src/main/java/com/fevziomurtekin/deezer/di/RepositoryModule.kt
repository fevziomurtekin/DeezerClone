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
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMainRepository(
        deezerClient: DeezerClient,
        deezerDao: DeezerDao,
        @IODispatcher dispatcher: CoroutineDispatcher
    ) = MainRepository(deezerClient, deezerDao, dispatcher)

    @Provides
    @Singleton
    fun provideFavoriteRepository(
        deezerDao: DeezerDao,
        @IODispatcher dispatcher: CoroutineDispatcher
    ) = FavoritesRepository(deezerDao, dispatcher)

    @Provides
    @Singleton
    fun providesSearchRepository(
        deezerClient: DeezerClient,
        deezerDao: DeezerDao,
        @IODispatcher dispatcher: CoroutineDispatcher
    ) = SearchRepository(deezerClient, deezerDao, dispatcher)

    @Provides
    @Singleton
    fun providesArtistRepository(
        deezerClient: DeezerClient,
        @IODispatcher dispatcher: CoroutineDispatcher
    ) = ArtistRepository(deezerClient, dispatcher)


    @Provides
    @Singleton
    fun providesAlbumRepository(
        deezerClient: DeezerClient,
        deezerDao: DeezerDao,
        @IODispatcher dispatcher: CoroutineDispatcher
    ) = AlbumRepository(deezerClient,deezerDao, dispatcher)

}
