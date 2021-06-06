package com.fevziomurtekin.deezer.viewmodel

import MainCoroutineRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import MockUtil
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.data.ArtistAlbumData
import com.fevziomurtekin.deezer.domain.local.DeezerDao
import com.fevziomurtekin.deezer.domain.network.DeezerClient
import com.fevziomurtekin.deezer.domain.network.DeezerService
import com.fevziomurtekin.deezer.ui.artist.ArtistRepository
import com.fevziomurtekin.deezer.ui.artist.details.albums.ArtistAlbumViewModel
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
class ArtistAlbumViewModelTest {
    private lateinit var viewModel: ArtistAlbumViewModel
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
        viewModel = ArtistAlbumViewModel(repository)
    }

    @Test
    fun fetchArtistAlbumTest() = runBlocking {
        val mockList = listOf(MockUtil.artistAlbum)

        val observer : Observer<ApiResult<List<ArtistAlbumData>>> = mock()
        val fetchedData : LiveData<ApiResult<List<ArtistAlbumData>>> = repository.fetchArtistAlbums(
          MockUtil.artistID).asLiveData()
        fetchedData.observeForever(observer)

        viewModel.fetchArtistAlbum(MockUtil.artistID)
        delay(500L)

        verify(observer).onChanged(ApiResult.Success(mockList))
        fetchedData.removeObserver(observer)
    }
}