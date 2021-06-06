package com.fevziomurtekin.deezer.repository

import MainCoroutineRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import MockUtil
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.data.AlbumData
import com.fevziomurtekin.deezer.domain.local.DeezerDao
import com.fevziomurtekin.deezer.domain.network.DeezerClient
import com.fevziomurtekin.deezer.domain.network.DeezerService
import com.fevziomurtekin.deezer.ui.album.AlbumRepository
import com.fevziomurtekin.deezer.ui.favorites.FavoritesRepository
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime

class FavoritesRepositoryTest {

  private lateinit var repository: FavoritesRepository
  private lateinit var albumRepository: AlbumRepository
  private lateinit var client: DeezerClient
  private val service: DeezerService = mock()
  private val deezerDao: DeezerDao = mock()

  @ExperimentalCoroutinesApi
  @get:Rule
  var coroutinesRule = MainCoroutineRule()

  @get:Rule
  var instantExecutorRule = InstantTaskExecutorRule()

  @ExperimentalCoroutinesApi
  @Before
  fun setup() {
    client = DeezerClient(service)
    repository = FavoritesRepository(deezerDao, Dispatchers.IO)
    albumRepository = AlbumRepository(client, deezerDao, Dispatchers.IO)
  }

  @ExperimentalTime
  @Test
  fun fetchFavoriteFromDaoTest() = runBlocking{
    val mockData = listOf(MockUtil.albumEntity)
    whenever(deezerDao.getFavorites()).thenReturn(mockData)

    repository.fetchFavorites().test {
      val result = expectItem()
      Assert.assertEquals(result,mockData)
      Assert.assertEquals((result as ApiResult.Success<List<AlbumData>>).data[0].title, "Alo")
      Assert.assertEquals(result.data[0].id , "425605922")
      Assert.assertEquals(result.data[1].title , "Geceler")
      Assert.assertEquals(result.data[1].id , "425605932")
    }

    verify(deezerDao, atLeastOnce()).getFavorites()
    verifyNoMoreInteractions(deezerDao)
  }

  @ExperimentalTime
  @Test
  fun insertFavoriteTrackFromDaoTest()= runBlocking {
    val mockData = MockUtil.albumEntity
    whenever(deezerDao.getFavorites()).thenReturn(emptyList())

    albumRepository.insertFavoritesData(mockData)
    repository.fetchFavorites().test {
      val result = expectItem()
      Assert.assertEquals(result,mockData)
      Assert.assertEquals((result as ApiResult.Success).data[0].title, "Alo")
      Assert.assertEquals(result.data[0].albumId , "425605922")
      Assert.assertEquals(result.data[1].title , "Geceler")
      Assert.assertEquals(result.data[1].albumId , "425605932")
    }
    verify(deezerDao, atLeastOnce()).insertTrack(mockData)
  }
}
