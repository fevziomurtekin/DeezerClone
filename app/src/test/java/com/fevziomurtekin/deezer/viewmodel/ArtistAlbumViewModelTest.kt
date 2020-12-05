package com.fevziomurtekin.deezer.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.fevziomurtekin.deezer.core.MockUtil
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.data.artistdetails.ArtistAlbumData
import com.fevziomurtekin.deezer.di.MainCoroutinesRule
import com.fevziomurtekin.deezer.domain.local.DeezerDao
import com.fevziomurtekin.deezer.domain.network.DeezerClient
import com.fevziomurtekin.deezer.domain.network.DeezerService
import com.fevziomurtekin.deezer.repository.DeezerRepository
import com.fevziomurtekin.deezer.ui.artist.details.albums.ArtistAlbumViewModel
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
class ArtistAlbumViewModelTest {
    private lateinit var viewModel: ArtistAlbumViewModel
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
        viewModel = ArtistAlbumViewModel(mainRepository)
    }

    @Test
    fun fetchArtistAlbumTest() = runBlocking {
        val mockList = listOf(MockUtil.artistAlbum)

        val observer : Observer<ApiResult<List<ArtistAlbumData>>> = mock()
        val fetchedData : LiveData<ApiResult<List<ArtistAlbumData>>> = mainRepository.fetchArtistAlbums(MockUtil.artistID).asLiveData()
        fetchedData.observeForever(observer)

        viewModel.fetchArtistAlbum(MockUtil.artistID)
        delay(500L)

        verify(observer).onChanged(ApiResult.Succes(mockList))
        fetchedData.removeObserver(observer)


    }



}