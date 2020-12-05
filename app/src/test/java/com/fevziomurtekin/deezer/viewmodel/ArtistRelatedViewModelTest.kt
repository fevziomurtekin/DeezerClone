package com.fevziomurtekin.deezer.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.fevziomurtekin.deezer.core.MockUtil
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.data.artistdetails.ArtistRelatedData
import com.fevziomurtekin.deezer.di.MainCoroutinesRule
import com.fevziomurtekin.deezer.domain.local.DeezerDao
import com.fevziomurtekin.deezer.domain.network.DeezerClient
import com.fevziomurtekin.deezer.domain.network.DeezerService
import com.fevziomurtekin.deezer.repository.DeezerRepository
import com.fevziomurtekin.deezer.ui.artist.details.related.ArtistRelatedViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ArtistRelatedViewModelTest {
    private lateinit var viewModel: ArtistRelatedViewModel
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
        viewModel = ArtistRelatedViewModel(mainRepository)
    }

    @Test
    fun fetchArtistDetailsTest() = runBlocking {
        val mockList = listOf(MockUtil.artistRelatedData)

        val observer : Observer<ApiResult<List<ArtistRelatedData>>> = mock()
        val fetchedData : LiveData<ApiResult<List<ArtistRelatedData>>> = mainRepository.fetchArtistRelated(MockUtil.artistID).asLiveData()
        fetchedData.observeForever(observer)

        viewModel.fetchArtistRelated(MockUtil.artistID)
        delay(500L)

        verify(observer).onChanged(ApiResult.Succes(mockList))
        fetchedData.removeObserver(observer)
    }

}