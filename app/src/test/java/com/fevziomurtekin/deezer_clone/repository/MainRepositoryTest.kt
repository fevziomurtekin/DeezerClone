package com.fevziomurtekin.deezer_clone.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import com.fevziomurtekin.deezer_clone.core.MockUtil
import com.fevziomurtekin.deezer_clone.core.Result
import com.fevziomurtekin.deezer_clone.data.genre.Data
import com.fevziomurtekin.deezer_clone.data.genre.GenreResponse
import com.fevziomurtekin.deezer_clone.di.MainCoroutinesRule
import com.fevziomurtekin.deezer_clone.domain.local.DeezerDao
import com.fevziomurtekin.deezer_clone.domain.network.DeezerClient
import com.fevziomurtekin.deezer_clone.domain.network.DeezerService
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailRepositoryTest {

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

       /*
        repository.fetchPokemonList(
        page = 0,
        onSuccess = {},
        onError = {}
        ).test {
        assertEquals(expectItem()[0].page, 0)
        assertEquals(expectItem()[0].name, "bulbasaur")
        assertEquals(expectItem(), mockPokemonList())
        expectComplete()
        }

        verify(pokemonDao, atLeastOnce()).getPokemonList(page_ = 0)
        verify(service, atLeastOnce()).fetchPokemonList()
        verify(pokemonDao, atLeastOnce()).insertPokemonList(mockData.results)*/
    }


}