package com.fevziomurtekin.deezer.repository

import MainCoroutineRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import MockUtil
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.data.SearchData
import com.fevziomurtekin.deezer.data.SearchResponse
import com.fevziomurtekin.deezer.domain.local.DeezerDao
import com.fevziomurtekin.deezer.domain.network.DeezerClient
import com.fevziomurtekin.deezer.domain.network.DeezerService
import com.fevziomurtekin.deezer.entities.SearchEntity
import com.fevziomurtekin.deezer.ui.search.SearchRepository
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

class SearchRepositoryTest {

  private lateinit var repository: SearchRepository
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
    repository = SearchRepository(client, deezerDao, Dispatchers.IO)
  }


  @ExperimentalTime
  @Test
  fun fetchRecentSearchFromDaoTest() = runBlocking {
    val mockData = listOf(MockUtil.recentData)
    whenever(deezerDao.getQueryList()).thenReturn(mockData)

    repository.fetchRecentSearch().test {
      val result = expectItem()
      Assert.assertEquals(result, ApiResult.Success(mockData))
      Assert.assertEquals((result as ApiResult.Success<List<SearchEntity>>).data[0].q, "Ezhel")
      Assert.assertEquals(result.data[0].searchId , "1")
      expectComplete()
    }

    verify(deezerDao, atLeastOnce()).getQueryList()
    verifyNoMoreInteractions(deezerDao)
  }


  @ExperimentalTime
  @Test
  fun insertSearchFromDaoTest() = runBlocking {
    val mockData = MockUtil.recentData
    whenever(deezerDao.getQueryList()).thenReturn(emptyList())

    /*"1","ezhel",12345.toLong()*/
    repository.insertSearch(mockData)
    repository.fetchRecentSearch().test {
      val result = expectItem()
      Assert.assertEquals(result, ApiResult.Success(mockData))
      Assert.assertEquals((result as ApiResult.Success<List<SearchEntity>>).data[0].q, "Ezhel")
      Assert.assertEquals(result.data[0].searchId , "1")
      expectComplete()
    }

    verify(deezerDao, atLeastOnce()).insertQuery(MockUtil.recentData)
  }

  @ExperimentalTime
  @Test
  fun fetchSearchFromNetworkTest() = runBlocking {
    val mockData = SearchResponse(data= listOf(MockUtil.searchData),next="",total=12)
    val returnData = Response.success(mockData)
    whenever(service.fetchSearchAlbum(MockUtil.query)).thenReturn(returnData)

    repository.fetchSearch(MockUtil.query).test {
      val result = expectItem()
      assertEquals(result, ApiResult.Success(listOf(MockUtil.searchData)))
      Assert.assertEquals((result as ApiResult.Success<List<SearchData>>).data[0].title , "İmkansızım")
      Assert.assertEquals(result.data[0].id , "51434782")
      Assert.assertEquals(result.data[0].recordType , "single")
      Assert.assertEquals(result.data[0].genreId , "116")
      expectComplete()
    }

    verify(service, atLeastOnce()).fetchSearchAlbum(MockUtil.query)
    verifyNoMoreInteractions(service)
  }
}
