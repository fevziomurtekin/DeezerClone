package com.fevziomurtekin.deezer_clone.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.fevziomurtekin.deezer_clone.core.MockUtil
import com.fevziomurtekin.deezer_clone.core.Result
import com.fevziomurtekin.deezer_clone.data.albumdetails.AlbumData
import com.fevziomurtekin.deezer_clone.data.search.SearchData
import com.fevziomurtekin.deezer_clone.di.MainCoroutinesRule
import com.fevziomurtekin.deezer_clone.domain.local.DeezerDao
import com.fevziomurtekin.deezer_clone.domain.network.DeezerClient
import com.fevziomurtekin.deezer_clone.domain.network.DeezerService
import com.fevziomurtekin.deezer_clone.repository.MainRepository
import com.fevziomurtekin.deezer_clone.ui.favorites.FavoritesViewModel
import com.fevziomurtekin.deezer_clone.ui.search.SearchViewModel
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
    private lateinit var mainRepository: MainRepository
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
        mainRepository = MainRepository(deezerClient,deezerDao)
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