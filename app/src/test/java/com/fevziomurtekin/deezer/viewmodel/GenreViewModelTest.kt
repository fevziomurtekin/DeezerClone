package com.fevziomurtekin.deezer.viewmodel

import MainCoroutineRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import MockUtil
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.data.Data
import com.fevziomurtekin.deezer.domain.local.DeezerDao
import com.fevziomurtekin.deezer.domain.network.DeezerClient
import com.fevziomurtekin.deezer.domain.network.DeezerService
import com.fevziomurtekin.deezer.ui.genre.GenreViewModel
import com.fevziomurtekin.deezer.ui.main.MainRepository
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
class GenreViewModelTest {
    private lateinit var viewModel: GenreViewModel
    private lateinit var repository: MainRepository

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
        repository = MainRepository(deezerClient,deezerDao, Dispatchers.IO)
        viewModel = GenreViewModel(repository)
    }

    @Test
    fun fetchGenreListTest() = runBlocking {
        val mockListEntity = MockUtil.genreEntityList
        val mockListData = MockUtil.genres
        whenever(deezerDao.getGenreList()).thenReturn(mockListEntity)

        val observer : Observer<ApiResult<List<Data>?>> = mock()
        val fetchedData : LiveData<ApiResult<List<Data>?>> = repository.fetchGenreList().asLiveData()
        fetchedData.observeForever(observer)

        viewModel.fetchResult()
        delay(500L)

        verify(deezerDao, atLeastOnce()).getGenreList()
        verify(observer).onChanged(ApiResult.Success(mockListData))
        fetchedData.removeObserver(observer)
    }
}