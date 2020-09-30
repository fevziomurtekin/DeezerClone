package com.fevziomurtekin.deezer_clone.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fevziomurtekin.deezer_clone.di.MainCoroutinesRule
import com.fevziomurtekin.deezer_clone.domain.local.DeezerDao
import com.fevziomurtekin.deezer_clone.domain.network.DeezerClient
import com.fevziomurtekin.deezer_clone.domain.network.DeezerService
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

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


       /* val mockData = mockPokemonInfo()
        whenever(pokemonInfoDao.getPokemonInfo(name_ = "bulbasaur")).thenReturn(null)
        whenever(service.fetchPokemonInfo(name = "bulbasaur")).thenReturn(ApiResponse.of { Response.success(mockData) })

        repository.fetchPokemonInfo(name = "bulbasaur", onSuccess = {}, onError = {}).test {
            assertEquals(expectItem()?.id, mockData.id)
            assertEquals(expectItem()?.name, mockData.name)
            assertEquals(expectItem(), mockData)
            expectComplete()
        }

        verify(pokemonInfoDao, atLeastOnce()).getPokemonInfo(name_ = "bulbasaur")
        verify(service, atLeastOnce()).fetchPokemonInfo(name = "bulbasaur")
        verify(pokemonInfoDao, atLeastOnce()).insertPokemonInfo(mockData)*/
    }


}