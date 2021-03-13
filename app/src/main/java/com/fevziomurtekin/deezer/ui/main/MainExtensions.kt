package com.fevziomurtekin.deezer.ui.main

import androidx.lifecycle.viewModelScope
import com.fevziomurtekin.deezer.R
import com.fevziomurtekin.deezer.core.Env
import com.fevziomurtekin.deezer.data.MediaPlayerState
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

internal fun MainActivity.navControllerListener() {
    navController.addOnDestinationChangedListener { _, destination, arguments ->
        /**
         * if mediaPlayer or volumeController visible, hidden views.
         */
        if(viewModel.isGoneMediaPlayer.get()){
            viewModel.hideMediaPlayer()
        }

        var name = getString(R.string.app_name)
        arguments?.let {
            name = it[Env.BUND_NAME].toString()
        }

        when(destination.id){
            R.id.genre->{ materialToolbar.title = getString(R.string.app_name) }
            R.id.search->{ materialToolbar.title = getString(R.string.search) }
            R.id.favorites->{ materialToolbar.title = getString(R.string.favorites) }
            R.id.genre_list,
            R.id.artist_details,
            R.id.album_details,->{ materialToolbar.title = name }
        }
    }
}

internal fun MainActivity.bottomNavigationListener() {
    /* BottomNavigationBar Click Listener */
    bottom_navigation.setOnNavigationItemReselectedListener {
        when(it.itemId){
            R.id.itemMusic->{ navController.navigate(R.id.action_genre) }
            R.id.itemSearch->{ navController.navigate(R.id.action_search) }
            R.id.itemFavorites->{  navController.navigate(R.id.favorites) }
        }
    }
}

internal fun MainViewModel.initMediaPlayer() {
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

fun MainViewModel.resumeMediaPlayer() {
    mediaPlayer.playWhenReady = true
}

fun MainViewModel.stopMediaPlayer() {
    mediaPlayer.playWhenReady = false
}

fun MainViewModel.forwardTrack() {
    if (!albumData.value.isNullOrEmpty() && albumData.value!!.size - 1 > positionTrack)
        ++positionTrack
    playMediaPlayer()
}

fun MainViewModel.previouslyTrack() {
    if (positionTrack > 0) positionTrack--
    playMediaPlayer()
}

internal fun MainViewModel.hideMediaPlayer() {
    isGoneMediaPlayer.set(false)
    stopMediaPlayer()
}


internal fun MainViewModel.playMediaPlayer(){
    viewModelScope.launch {
        val musicUri =albumData.value?.get(0)?.preview
        val extractorMediaSource = ExtractorMediaSource.Factory(dataSourceFactory)
            .setExtractorsFactory(DefaultExtractorsFactory())
            .createMediaSource(MediaItem.fromUri(musicUri.toString()))
        mediaPlayer.prepare(extractorMediaSource)
        mediaPlayer.playWhenReady = true
    }
}
