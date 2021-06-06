package com.fevziomurtekin.deezer.viewmodel

import MainCoroutineRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import MockUtil
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.data.AlbumData
import com.fevziomurtekin.deezer.domain.local.DeezerDao
import com.fevziomurtekin.deezer.domain.network.DeezerClient
import com.fevziomurtekin.deezer.domain.network.DeezerService
import com.fevziomurtekin.deezer.ui.album.AlbumDetailsViewModel
import com.fevziomurtekin.deezer.ui.album.AlbumRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
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
class AlbumDetailsViewModelTest {
    private lateinit var viewModel: AlbumDetailsViewModel
    private lateinit var repository: AlbumRepository

    private lateinit var deezerClient: DeezerClient

    @MockK
    private lateinit var deezerService: DeezerService
    @MockK
    private lateinit var deezerDao: DeezerDao

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
        repository = AlbumRepository(deezerClient,deezerDao, Dispatchers.IO)
        viewModel = AlbumDetailsViewModel(repository)
    }

    @Test
    fun fetchAlbumListTest() = runBlocking {
        val mockList = listOf(MockUtil.album)

        val observer : Observer<ApiResult<List<AlbumData>>> = mock()
        val fetchedData : LiveData<ApiResult<List<AlbumData>>> = repository.fetchAlbumTracks("302127").asLiveData()
        fetchedData.observeForever(observer)

        viewModel.fetchingAlbumDatas(MockUtil.albumID)
        delay(500L)

        verify(observer).onChanged(ApiResult.Success(mockList))
        fetchedData.removeObserver(observer)


    }



}