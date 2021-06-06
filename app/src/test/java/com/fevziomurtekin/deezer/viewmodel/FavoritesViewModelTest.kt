package com.fevziomurtekin.deezer.viewmodel

import MainCoroutineRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import MockUtil
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.domain.local.DeezerDao
import com.fevziomurtekin.deezer.domain.network.DeezerClient
import com.fevziomurtekin.deezer.domain.network.DeezerService
import com.fevziomurtekin.deezer.entities.AlbumEntity
import com.fevziomurtekin.deezer.ui.favorites.FavoritesRepository
import com.fevziomurtekin.deezer.ui.favorites.FavoritesViewModel
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class FavoritesViewModelTest {
    private lateinit var viewModel: FavoritesViewModel
    private lateinit var repository: FavoritesRepository

    @MockK
    private lateinit var deezerService: DeezerService
    @MockK
    private lateinit var deezerDao: DeezerDao

    private lateinit var deezerClient: DeezerClient

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setup(){
        MockKAnnotations.init(this, relaxed = true)
        deezerClient = DeezerClient(deezerService)
        repository = FavoritesRepository(deezerDao, Dispatchers.IO)
        viewModel = FavoritesViewModel(repository)
    }

    @Test
    fun fetchSearchTest() = runBlocking {
        val mockData = MockUtil.albumEntity
        val mockList = listOf(MockUtil.albumEntity)
        whenever(deezerDao.getFavorites()).thenReturn(listOf(mockData))

        val observer : Observer<ApiResult<List<AlbumEntity>>> = mock()
        val fetchedData : LiveData<ApiResult<List<AlbumEntity>>> = repository.fetchFavorites().asLiveData()
        fetchedData.observeForever(observer)

        viewModel.fetchFavorites()
        delay(500L)

        verify(deezerDao.insertTrack(mockData))
        verify(deezerDao, atLeastOnce()).getFavorites()
        fetchedData.removeObserver(observer)
    }

}