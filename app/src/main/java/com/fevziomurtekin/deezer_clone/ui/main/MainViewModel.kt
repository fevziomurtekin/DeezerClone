package com.fevziomurtekin.deezer_clone.ui.main

import android.app.Application
import android.net.Uri
import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.fevziomurtekin.deezer_clone.core.Result
import com.fevziomurtekin.deezer_clone.data.albumdetails.AlbumData
import com.fevziomurtekin.deezer_clone.data.mediaplayer.MediaPlayerState
import com.fevziomurtekin.deezer_clone.repository.MainRepository
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.offline.DownloadHelper.createMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util.getUserAgent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber


class MainViewModel @ViewModelInject constructor(
    private val app: Application,
    private val mainRepository: MainRepository
): ViewModel(){

    var genreListLiveData: LiveData<Result<Any>> = MutableLiveData()
    var isSplash:MutableLiveData<Boolean> = MutableLiveData()
    var isGoneMediaPlayer :ObservableBoolean = ObservableBoolean(false)
    var albumData:MutableLiveData<MutableMap<Int,List<AlbumData>>> = MutableLiveData()

    private val mediaPlayer = ExoPlayerFactory.newSimpleInstance(app.applicationContext)
    private val dataSourceFactory = DefaultDataSourceFactory(app.applicationContext, getUserAgent(app.applicationContext,"DeezerClone"))

    var mediaPlayerState:MutableLiveData<MediaPlayerState> = MutableLiveData()


    init {
        Timber.d("init mainViewModel")
        isSplash.value = true

        mediaPlayer.addListener(object : Player.EventListener {
            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                val state = when {
                    playbackState == ExoPlayer.STATE_READY && mediaPlayer.playWhenReady -> MediaPlayerState.PLAYING
                    playbackState == ExoPlayer.STATE_READY && mediaPlayer.playWhenReady.not() -> MediaPlayerState.PAUSED
                    playbackState == ExoPlayer.STATE_BUFFERING -> MediaPlayerState.BUFFERING
                    else -> MediaPlayerState.ERROR
                }

                mediaPlayerState.value = state
            }

            override fun onPlayerError(error: ExoPlaybackException) {
                mediaPlayerState.value = MediaPlayerState.ERROR
            }
        })
    }

    fun fetchGenreList(){
        viewModelScope.launch {
            genreListLiveData = mainRepository.fetchGenreList()
                .asLiveData(viewModelScope.coroutineContext+Dispatchers.Default)

        }
    }

    fun playMusic(){
        viewModelScope.launch {
            val trackPos:Int = albumData.value?.keys?.first()!!
            val data = (albumData.value)?.values?.first()?.get(trackPos)
            val musicUri = data?.preview
            val mediaSource =  ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(MediaItem.fromUri(Uri.parse(musicUri)))
            mediaPlayer.playWhenReady = true
            mediaPlayer.prepare(mediaSource)
        }
    }

    fun resume() {
        mediaPlayer.playWhenReady = true
    }

    fun stop() {
        mediaPlayer.playWhenReady = false
    }

    fun forwardTrack(){

        albumData.value?.keys?.apply {
            first().plus(1)
        }

        playMusic()
    }

    fun previouslyTrack(){
        albumData.value?.keys?.apply {
            first().minus(1)
        }
        playMusic()
    }

}