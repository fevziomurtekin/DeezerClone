package com.fevziomurtekin.deezer.repository

import MainCoroutineRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import MockUtil
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.data.*
import com.fevziomurtekin.deezer.domain.local.DeezerDao
import com.fevziomurtekin.deezer.domain.network.DeezerClient
import com.fevziomurtekin.deezer.domain.network.DeezerService
import com.fevziomurtekin.deezer.ui.artist.ArtistRepository
import com.nhaarman.mockitokotlin2.*
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import kotlin.time.ExperimentalTime

class ArtistRepositoryTest {

  private lateinit var repository: ArtistRepository
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
    repository = ArtistRepository(client, Dispatchers.IO)
  }


  @ExperimentalTime
  @Test
  fun fetchArtistListFromNetworkTest() = runBlocking {
    val mockData = ArtistsResponse(listOf(MockUtil.artist))
    val returnData = Response.success(mockData)
    whenever(service.fetchArtistList(MockUtil.genreID)).thenReturn(returnData)

    repository.fetchArtistList(MockUtil.genreID).test {
      val result = expectItem()
      assertEquals(result, ApiResult.Success(listOf(MockUtil.artist)))
      Assert.assertEquals((result as ApiResult.Success<List<ArtistData>>).data[0].name , "Ezhel")
      Assert.assertEquals(result.data[0].id , "8354140")
      Assert.assertEquals(result.data[1].name , "Sagopa Kajmer")
      Assert.assertEquals(result.data[1].id , "12934")
      expectComplete()
    }

    verify(service, atLeastOnce()).fetchArtistList(MockUtil.genreID)
    verifyNoMoreInteractions(service)
  }



  @ExperimentalTime
  @Test
  fun fetchArtistDetailsFromNetworkTest() = runBlocking {
    val mockData = MockUtil.artistDetails
    val returnData = Response.success(mockData)
    whenever(service.fetchArtistDetails(MockUtil.artistID)).thenReturn(returnData)

    repository.fetchArtistDetails(MockUtil.artistID).test {
      val result = expectItem()
      assertEquals(result, ApiResult.Success(mockData))
      Assert.assertEquals((result as ApiResult.Success<ArtistDetailResponse>).data.name , "Ezhel")
      Assert.assertEquals(result.data.id , "8354140")
      expectComplete()
    }

    verify(service, atLeastOnce()).fetchArtistList(MockUtil.artistID)
    verifyNoMoreInteractions(service)
  }

  @ExperimentalTime
  @Test
  fun fetchArtistAlbumsFromNetworkTest() = runBlocking {
    val mockData = ArtistAlbumResponse(data= listOf(MockUtil.artistAlbum),next= "",total=20)
    val returnData = Response.success(mockData)
    whenever(service.fetchArtistAlbums(MockUtil.artistID)).thenReturn(returnData)

    repository.fetchArtistAlbums(MockUtil.artistID).test {
      val result = expectItem()
      assertEquals(result, ApiResult.Success(listOf(MockUtil.artistAlbum)))
      Assert.assertEquals((result as ApiResult.Success<List<ArtistAlbumData>>).data[0].title , "Müptezhel")
      Assert.assertEquals(result.data[0].id , "51174732")
      Assert.assertEquals(result.data[1].title , "Made In Turkey")
      Assert.assertEquals(result.data[1].id , "155597932")
      expectComplete()
    }

    verify(service, atLeastOnce()).fetchArtistAlbums(MockUtil.artistID)
    verifyNoMoreInteractions(service)
  }

  @ExperimentalTime
  @Test
  fun fetchArtistRelatedFromNetworkTest() = runBlocking {
    val mockData = ArtistRelatedResponse(data= listOf(MockUtil.artistRelatedData),total=20)
    val returnData = Response.success(mockData)
    whenever(service.fetchArtistRelated(MockUtil.artistID)).thenReturn(returnData)

    repository.fetchArtistRelated(MockUtil.artistID).test {
      val result = expectItem()
      assertEquals(result, ApiResult.Success(listOf(MockUtil.artistRelatedData)))
      Assert.assertEquals((result as ApiResult.Success<List<ArtistRelatedData>>).data[0].name , "Murda")
      Assert.assertEquals(result.data[0].id , "389038")
      Assert.assertEquals(result.data[1].name , "Reynmen")
      Assert.assertEquals(result.data[1].id , "13136341")
      expectComplete()
    }

    verify(service, atLeastOnce()).fetchArtistRelated(MockUtil.artistID)
    verifyNoMoreInteractions(service)
  }

}


