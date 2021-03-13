package com.fevziomurtekin.deezer

import com.fevziomurtekin.deezer.domain.local.DeezerDaoTest
import com.fevziomurtekin.deezer.domain.network.DeezerServiceTest
import com.fevziomurtekin.deezer.repository.*
import com.fevziomurtekin.deezer.viewmodel.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

@ExperimentalCoroutinesApi
@RunWith(Suite::class)
@Suite.SuiteClasses(
    DeezerDaoTest::class,
    DeezerServiceTest::class,
    MainRepositoryTest::class,
    AlbumRepositoryTest::class,
    ArtistRepositoryTest::class,
    FavoritesRepositoryTest::class,
    SearchRepositoryTest::class,
    AlbumDetailsViewModelTest::class,
    ArtistAlbumViewModelTest::class,
    ArtistDetailsViewModelTest::class,
    ArtistViewModelTest::class,
    FavoritesViewModelTest::class,
    GenreViewModelTest::class,
    MainViewModelTest::class,
    SearchViewModelTest::class,
)
class SuiteClass