package com.fevziomurtekin.deezer.viewmodel

import MainCoroutineRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import MockUtil
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.data.SearchData
import com.fevziomurtekin.deezer.domain.local.DeezerDao
import com.fevziomurtekin.deezer.domain.network.DeezerClient
import com.fevziomurtekin.deezer.domain.network.DeezerService
import com.fevziomurtekin.deezer.ui.search.SearchRepository
import com.fevziomurtekin.deezer.ui.search.SearchViewModel
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
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class SearchViewModelTest {
    private lateinit var viewModel:SearchViewModel
    private lateinit var searchRepository: SearchRepository

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
        searchRepository = SearchRepository(deezerClient,deezerDao,Dispatchers.IO)
        viewModel = SearchViewModel(searchRepository)
    }

    @Test
    fun fetchSearchTest() = runBlocking {
        val mockData = MockUtil.recentData
        val mockList = listOf(MockUtil.searchData)
        whenever(deezerDao.getQueryList()).thenReturn(listOf(mockData))

        val observer : Observer<ApiResult<List<SearchData>>> = mock()
        val fetchedData : LiveData<ApiResult<List<SearchData>>> = searchRepository.fetchSearch(
          MockUtil.query).asLiveData()
        fetchedData.observeForever(observer)

        viewModel.fetchingRecentSearch()
        delay(500L)

        verify(deezerDao.insertQuery(mockData))
        verify(deezerDao, atLeastOnce()).getGenreList()
        verify(observer).onChanged(ApiResult.Success(mockList))
        fetchedData.removeObserver(observer)
    }
}