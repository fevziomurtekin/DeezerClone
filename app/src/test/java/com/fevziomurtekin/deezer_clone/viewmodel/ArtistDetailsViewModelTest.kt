package com.fevziomurtekin.deezer_clone.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.fevziomurtekin.deezer_clone.core.MockUtil
import com.fevziomurtekin.deezer_clone.core.Result
import com.fevziomurtekin.deezer_clone.data.artistdetails.ArtistAlbumData
import com.fevziomurtekin.deezer_clone.data.artistdetails.ArtistDetailResponse
import com.fevziomurtekin.deezer_clone.di.MainCoroutinesRule
import com.fevziomurtekin.deezer_clone.domain.local.DeezerDao
import com.fevziomurtekin.deezer_clone.domain.network.DeezerClient
import com.fevziomurtekin.deezer_clone.domain.network.DeezerService
import com.fevziomurtekin.deezer_clone.repository.MainRepository
import com.fevziomurtekin.deezer_clone.ui.artistdetails.albums.ArtistAlbumViewModel
import com.fevziomurtekin.deezer_clone.ui.artistdetails.detail.ArtistDetailsViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ArtistDetailsViewModelTest {
    private lateinit var viewModel: ArtistDetailsViewModel
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
        viewModel = ArtistDetailsViewModel(mainRepository)
    }

    @Test
    fun fetchArtistDetailsTest() = runBlocking {
        val mockList = MockUtil.artistDetails

        val observer : Observer<Result<ArtistDetailResponse>> = mock()
        val fetchedData : LiveData<Result<ArtistDetailResponse>> = mainRepository.fetchArtistDetails("8354140").asLiveData()
        fetchedData.observeForever(observer)

        viewModel.fetchArtistDetails("8354140")
        delay(500L)

        verify(observer).onChanged(Result.Succes(mockList))
        fetchedData.removeObserver(observer)


    }



}