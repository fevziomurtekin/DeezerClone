package com.fevziomurtekin.deezer_clone.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import app.cash.turbine.test
import com.fevziomurtekin.deezer_clone.core.MockUtil
import com.fevziomurtekin.deezer_clone.core.Result
import com.fevziomurtekin.deezer_clone.data.genre.Data
import com.fevziomurtekin.deezer_clone.data.genre.GenreResponse
import com.fevziomurtekin.deezer_clone.di.MainCoroutinesRule
import com.fevziomurtekin.deezer_clone.domain.local.DeezerDao
import com.fevziomurtekin.deezer_clone.domain.network.DeezerClient
import com.fevziomurtekin.deezer_clone.domain.network.DeezerService
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.CompletableDeferred
import org.junit.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
class MainRepositoryTest {

    private lateinit var repository: MainRepository
    private lateinit var client: DeezerClient
    private val service: DeezerService = mock()
    private val deezerDao: DeezerDao = mock()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        client = DeezerClient(service)
        repository = MainRepository(client, deezerDao)
    }

    @ExperimentalTime
    @Test
    fun fetchGenreListFromNetworkTest() = runBlocking {
        val mockData = GenreResponse(MockUtil.genres)
        val deferredData = CompletableDeferred(mockData)
        whenever(deezerDao.getQueryList()).thenReturn(emptyList())
        whenever(service.fetchGenreList()).thenReturn(deferredData)

        /** core testing */
       /* val result = repository.fetchGenreList().asLiveData().value
        Assert.assertEquals(result, Result.Succes(MockUtil.genres))*/

        /** add to turbine library.*/
        repository.fetchGenreList().test {
            val result = expectItem()
            assertEquals(result, Result.Succes(MockUtil.genres))
            assertEquals((result as Result.Succes<List<Data>>).data[0].name , "All")
            assertEquals(result.data[0].id , "0")
            assertEquals(result.data[1].name , "Pop")
            assertEquals(result.data[1].id , "132")
            expectComplete()
        }

        verify(deezerDao, atLeastOnce()).getGenreList()
        verify(service, atLeastOnce()).fetchGenreList()
        verify(deezerDao, atLeastOnce()).insertGenreList(MockUtil.genres)

    }


}