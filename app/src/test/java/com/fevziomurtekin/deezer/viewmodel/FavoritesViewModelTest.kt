package com.fevziomurtekin.deezer.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.fevziomurtekin.deezer.core.MockUtil
import com.fevziomurtekin.deezer.data.albumdetails.AlbumData
import com.fevziomurtekin.deezer.di.MainCoroutinesRule
import com.fevziomurtekin.deezer.domain.local.DeezerDao
import com.fevziomurtekin.deezer.domain.network.DeezerClient
import com.fevziomurtekin.deezer.domain.network.DeezerService
import com.fevziomurtekin.deezer.repository.DeezerRepository
import com.fevziomurtekin.deezer.ui.favorites.FavoritesViewModel
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class FavoritesViewModelTest {
    private lateinit var viewModel: FavoritesViewModel
    private lateinit var mainRepository: DeezerRepository
    private val deezerService: DeezerService = mockk()
    private val deezerClient = DeezerClient(deezerService)
    private val deezerDao: DeezerDao = mockk()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setup(){
        mainRepository = DeezerRepository(deezerClient,deezerDao)
        viewModel = FavoritesViewModel(mainRepository)
    }

    @Test
    fun fetchSearchTest() = runBlocking {
        val mockData = MockUtil.album
        val mockList = listOf(MockUtil.album)
        whenever(deezerDao.getFavorites()).thenReturn(listOf(mockData))

        val observer : Observer<List<AlbumData>> = mock()
        val fetchedData : LiveData<List<AlbumData>> = mainRepository.fetchFavorites().asLiveData()
        fetchedData.observeForever(observer)

        viewModel.fetchFavorites()
        delay(500L)

        verify(deezerDao.insertTrack(mockData))
        verify(deezerDao, atLeastOnce()).getFavorites()
        verify(observer).onChanged(mockList)
        fetchedData.removeObserver(observer)


    }



}