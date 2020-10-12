package com.fevziomurtekin.deezer.ui.main

import android.accounts.NetworkErrorException
import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.fevziomurtekin.deezer.core.Result
import com.fevziomurtekin.deezer.data.albumdetails.AlbumData
import com.fevziomurtekin.deezer.data.mediaplayer.MediaPlayerState
import com.fevziomurtekin.deezer.repository.DeezerRepository
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util.getUserAgent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber


class MainViewModel @ViewModelInject constructor(
    private val app: Application,
    private val mainRepository: DeezerRepository
): ViewModel(){

    var genreListLiveData: LiveData<Result<Any>> = MutableLiveData()
    var isSplash:MutableLiveData<Boolean> = MutableLiveData()
    var isGoneMediaPlayer :ObservableBoolean = ObservableBoolean(false)
    var albumData:MutableLiveData<List<AlbumData>> = MutableLiveData()
    var positionTrack = 0
    val isNetworkError = MutableLiveData(false)

    private val mediaPlayer = SimpleExoPlayer.Builder(app.applicationContext).build()
    private val dataSourceFactory = DefaultDataSourceFactory(app.applicationContext, getUserAgent(app.applicationContext,"DeezerClone"))

    var mediaPlayerState:MutableLiveData<MediaPlayerState> = MutableLiveData()

    init {
        Timber.d("init mainViewModel")
        isSplash.value = true
        mediaPlayerState.value = MediaPlayerState.BUFFERING
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
            try {
                genreListLiveData = mainRepository.fetchGenreList()
                    .asLiveData(viewModelScope.coroutineContext+Dispatchers.Default)
            }catch (e:NetworkErrorException){
                isNetworkError.value = true
            }

        }
    }

    fun playMusic(){
        viewModelScope.launch {
            val musicUri =albumData.value?.get(0)?.preview
            Timber.d("musicUri : $musicUri")
            val extractorMediaSource = ExtractorMediaSource.Factory(dataSourceFactory)
                .setExtractorsFactory(DefaultExtractorsFactory())
                .createMediaSource(MediaItem.fromUri(musicUri.toString()))
            mediaPlayer.prepare(extractorMediaSource)
            mediaPlayer.playWhenReady = true
        }
    }

    fun resume() {
        mediaPlayer.playWhenReady = true
    }

    fun stop() {
        mediaPlayer.playWhenReady = false
    }

    fun forwardTrack(){
        Timber.d("forwardTrack : $positionTrack")
        if(!albumData.value.isNullOrEmpty() && albumData.value!!.size-1 > positionTrack)
            ++positionTrack

        Timber.d("forwardTrack : $positionTrack")
        playMusic()
    }

    fun previouslyTrack(){
        Timber.d("previouslyTrack : $positionTrack")
        if(positionTrack>0) positionTrack--
        Timber.d("previouslyTrack : $positionTrack")
        playMusic()
    }

    fun hideMediaPlayer(){
        isGoneMediaPlayer.set(false)
        stop()
    }
}