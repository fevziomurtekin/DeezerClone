package com.fevziomurtekin.deezer_clone.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.fevziomurtekin.deezer_clone.core.MockUtil
import com.fevziomurtekin.deezer_clone.core.Result
import com.fevziomurtekin.deezer_clone.data.genre.Data
import com.fevziomurtekin.deezer_clone.data.mediaplayer.MediaPlayerState
import com.fevziomurtekin.deezer_clone.di.MainCoroutinesRule
import com.fevziomurtekin.deezer_clone.domain.local.DeezerDao
import com.fevziomurtekin.deezer_clone.domain.network.DeezerClient
import com.fevziomurtekin.deezer_clone.domain.network.DeezerService
import com.fevziomurtekin.deezer_clone.repository.MainRepository
import com.fevziomurtekin.deezer_clone.ui.main.MainActivity
import com.fevziomurtekin.deezer_clone.ui.main.MainViewModel
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import androidx.test.core.app.ActivityScenario.launch


/**
*   While writing some tests, I used the tests at
*   "https://github.com/Koducation/AndroidCourse101/blob/master/app/src/test/java/com/koducation/androidcourse101/PlayerViewStateTest.kt."
*   Thank you @muratcanbur.
 * */

@ExperimentalCoroutinesApi
class MainViewModelTest {
    private val application:Application = mock()
    private lateinit var viewModel:MainViewModel
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
        mainRepository = MainRepository(deezerClient, deezerDao)
        viewModel = MainViewModel(application, mainRepository)
    }

    @Test
    fun fetchGenreListTest() = runBlocking {
        val mockList = MockUtil.genres
        whenever(deezerDao.getGenreList()).thenReturn(mockList)

        val observer : Observer<Result<List<Data>>> = mock()
        val fetchedData : LiveData<Result<List<Data>>> = mainRepository.fetchGenreList().asLiveData()
        fetchedData.observeForever(observer)

        viewModel.fetchGenreList()
        delay(500L)

        verify(deezerDao, atLeastOnce()).getGenreList()
        verify(observer).onChanged(Result.Succes(mockList))
        fetchedData.removeObserver(observer)
    }

    @Test
    fun `given mediaPlayer PLAYING state, when ibtnPlayPlayer is called, then return match two values`() {

        /*  Given */
        val playerState = MediaPlayerState.PLAYING
        viewModel.playMusic()

        /* When */
        val actualResult = viewModel.mediaPlayerState.value.let {
            it?:MediaPlayerState.ERROR
        }

        /* Then */
        Assert.assertEquals(playerState, actualResult)
    }

    @Test
    fun `given mediaPlayer PLAYING state, when onBackPressed action, then return mediaPlayerState PAUSED`(){
        val scenario = launch(MainActivity::class.java)

        /* When */
        scenario.onActivity {a->
            a.onBackPressed()
        }
        val actualResult = viewModel.mediaPlayerState.value.let {
            it?:MediaPlayerState.ERROR
        }


        Assert.assertEquals(actualResult,MediaPlayerState.PAUSED)
    }

}