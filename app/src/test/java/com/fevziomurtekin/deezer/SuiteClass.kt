package com.fevziomurtekin.deezer

import com.fevziomurtekin.deezer.domain.local.DeezerDaoTest
import com.fevziomurtekin.deezer.domain.network.DeezerServiceTest
import com.fevziomurtekin.deezer.repository.AlbumRepositoryTest
import com.fevziomurtekin.deezer.repository.ArtistRepositoryTest
import com.fevziomurtekin.deezer.repository.FavoritesRepositoryTest
import com.fevziomurtekin.deezer.repository.MainRepositoryTest
import com.fevziomurtekin.deezer.repository.SearchRepositoryTest
import com.fevziomurtekin.deezer.viewmodel.AlbumDetailsViewModelTest
import com.fevziomurtekin.deezer.viewmodel.ArtistAlbumViewModelTest
import com.fevziomurtekin.deezer.viewmodel.ArtistDetailsViewModelTest
import com.fevziomurtekin.deezer.viewmodel.ArtistViewModelTest
import com.fevziomurtekin.deezer.viewmodel.FavoritesViewModelTest
import com.fevziomurtekin.deezer.viewmodel.GenreViewModelTest
import com.fevziomurtekin.deezer.viewmodel.MainViewModelTest
import com.fevziomurtekin.deezer.viewmodel.SearchViewModelTest
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
    DeezerDaoTest::class,
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