package com.fevziomurtekin.deezer.ui.main

import android.accounts.NetworkErrorException
import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.data.AlbumData
import com.fevziomurtekin.deezer.data.Data
import com.fevziomurtekin.deezer.data.MediaPlayerState
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util.getUserAgent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

private const val APPLICATION_NAME = "DeezerClone"

class MainViewModel @ViewModelInject constructor(
    app: Application,
    private val mainRepository: MainRepository
): ViewModel(){

    @VisibleForTesting var genreListLiveData: LiveData<ApiResult<List<Data>?>> = MutableLiveData()
    var isSplash: MutableLiveData<Boolean> = MutableLiveData()
    var isGoneMediaPlayer :ObservableBoolean = ObservableBoolean(false)
    var albumData:MutableLiveData<List<AlbumData>> = MutableLiveData()
    var positionTrack = 0
    val isNetworkError = MutableLiveData(false)

    val mediaPlayer = SimpleExoPlayer.Builder(app.applicationContext).build()
    internal val dataSourceFactory =
        DefaultDataSourceFactory(
            app.applicationContext,
            getUserAgent(app.applicationContext, APPLICATION_NAME))

    var mediaPlayerState:MutableLiveData<MediaPlayerState> = MutableLiveData()

    init {
        isSplash.value = true
        initMediaPlayer()
    }

    fun fetchGenreList(){
        viewModelScope.launch {
            try {
                genreListLiveData = mainRepository.fetchGenreList()
                    .asLiveData(viewModelScope.coroutineContext+Dispatchers.Default)
            }catch (e:NetworkErrorException){
                isNetworkError.value = true
                Timber.e(e)
            }

        }
    }
}
