package com.fevziomurtekin.deezer.viewmodel

import MainCoroutineRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import MockUtil
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.data.ArtistData
import com.fevziomurtekin.deezer.domain.local.DeezerDao
import com.fevziomurtekin.deezer.domain.network.DeezerClient
import com.fevziomurtekin.deezer.domain.network.DeezerService
import com.fevziomurtekin.deezer.ui.artist.ArtistRepository
import com.fevziomurtekin.deezer.ui.artist.profile.ArtistViewModel
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
class ArtistViewModelTest {
    private lateinit var viewModel: ArtistViewModel
    private lateinit var repository: ArtistRepository

    @MockK
    private lateinit var deezerService: DeezerService

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
        repository = ArtistRepository(deezerClient, Dispatchers.IO)
        viewModel = ArtistViewModel(repository)
    }

    @Test
    fun fetchArtistListTest() = runBlocking {
        val mockList = listOf(MockUtil.artist)

        val observer : Observer<ApiResult<List<ArtistData>>> = mock()
        val fetchedData : LiveData<ApiResult<List<ArtistData>>> = repository.fetchArtistList(
          MockUtil.genreID).asLiveData()
        fetchedData.observeForever(observer)

        viewModel.fetchResult(MockUtil.genreID)
        delay(500L)

        verify(observer).onChanged(ApiResult.Success(mockList))
        fetchedData.removeObserver(observer)


    }



}