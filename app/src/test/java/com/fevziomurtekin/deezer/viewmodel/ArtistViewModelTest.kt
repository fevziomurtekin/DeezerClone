package com.fevziomurtekin.deezer.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.fevziomurtekin.deezer.core.MockUtil
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.data.ArtistData
import com.fevziomurtekin.deezer.di.MainCoroutinesRule
import com.fevziomurtekin.deezer.domain.local.DeezerDao
import com.fevziomurtekin.deezer.domain.network.DeezerClient
import com.fevziomurtekin.deezer.domain.network.DeezerService
import com.fevziomurtekin.deezer.ui.artist.ArtistRepository
import com.fevziomurtekin.deezer.ui.artist.profile.ArtistViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.mockk.mockk
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
        repository = ArtistRepository(deezerClient,deezerDao)
        viewModel = ArtistViewModel(repository)
    }

    @Test
    fun fetchArtistListTest() = runBlocking {
        val mockList = listOf(MockUtil.artist)

        val observer : Observer<ApiResult<List<ArtistData>>> = mock()
        val fetchedData : LiveData<ApiResult<List<ArtistData>>> = repository.fetchArtistList(MockUtil.genreID).asLiveData()
        fetchedData.observeForever(observer)

        viewModel.fetchResult(MockUtil.genreID)
        delay(500L)

        verify(observer).onChanged(ApiResult.Success(mockList))
        fetchedData.removeObserver(observer)


    }



}