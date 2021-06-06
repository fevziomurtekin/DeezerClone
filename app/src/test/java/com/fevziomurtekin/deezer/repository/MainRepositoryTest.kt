package com.fevziomurtekin.deezer.repository

import MainCoroutineRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import MockUtil
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.core.mapper.mapper
import com.fevziomurtekin.deezer.data.Data
import com.fevziomurtekin.deezer.data.GenreResponse
import com.fevziomurtekin.deezer.domain.local.DeezerDao
import com.fevziomurtekin.deezer.domain.network.DeezerClient
import com.fevziomurtekin.deezer.domain.network.DeezerService
import com.fevziomurtekin.deezer.ui.main.MainRepository
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
class MainRepositoryTest {

    private lateinit var repository: MainRepository
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
        repository = MainRepository(client, deezerDao, Dispatchers.IO)
    }

    @ExperimentalTime
    @Test
    fun fetchGenreListFromNetworkTest() = runBlocking {
        val mockData = GenreResponse(MockUtil.genres)
        val returnData = Response.success(mockData)
        whenever(deezerDao.getGenreList()).thenReturn(emptyList())
        whenever(service.fetchGenreList()).thenReturn(returnData)

        /** add to turbine library.*/
        repository.fetchGenreList().test {
            val result = expectItem()
            assertEquals(result, ApiResult.Success(MockUtil.genres))
            assertEquals((result as ApiResult.Success<List<Data>>).data[0].name , "All")
            assertEquals(result.data[0].id , "0")
            assertEquals(result.data[1].name , "Pop")
            assertEquals(result.data[1].id , "132")
            expectComplete()
        }

        verify(deezerDao, atLeastOnce()).getGenreList()
        verify(service, atLeastOnce()).fetchGenreList()
        verify(deezerDao, atLeastOnce()).insertGenreList(MockUtil.genres.mapper())

    }
}