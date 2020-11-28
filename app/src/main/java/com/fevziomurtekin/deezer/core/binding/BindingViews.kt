package com.fevziomurtekin.deezer.core

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.view.isGone
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import coil.load
import coil.size.Scale
import coil.transform.BlurTransformation
import com.fevziomurtekin.deezer.R
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.core.extensions.isNotNull
import com.fevziomurtekin.deezer.core.extensions.isSuccessAndNotNull
import com.fevziomurtekin.deezer.data.albumdetails.AlbumData
import com.fevziomurtekin.deezer.data.artistdetails.ArtistDetailResponse
import com.fevziomurtekin.deezer.data.mediaplayer.MediaPlayerState
import com.fevziomurtekin.deezer.entities.AlbumEntity
import timber.log.Timber

/**
 * Help in the development of this class, the application named 'Pokedex' @Skydoves user has helped.
 * https://github.com/skydoves/Pokedex/blob/master/app/src/main/java/com/skydoves/pokedex/binding/ViewBinding.kt
 * */


/* Load to Image with url inside to layout files
* Recyclerview item. Bluring imageview.
* */
@BindingAdapter("bindImageUrl")
fun bindLoadImageUrl(view: ImageView, url: String) {
    Timber.d("Binding url : $url ")
    view.load(url){
        crossfade(true)
        scale(Scale.FIT)
        placeholder(R.color.colorPrimary)
        transformations(BlurTransformation(view.context,6f,0.3f))
    }
}

@BindingAdapter("bindImageArtist")
fun bindLoadImageArtistDetails(view: ImageView, results:LiveData<ApiResult<ArtistDetailResponse>>) {
    when (results.value) {
        ApiResult.Loading, is ApiResult.Error-> {/* Nothing */ }
        is ApiResult.Success -> {
            val imgUrl = ((results.value as ApiResult.Success<ArtistDetailResponse>).data.picture_big)
            Timber.d("Binding url : $imgUrl ")
            view.load(imgUrl){
                crossfade(true)
                placeholder(R.color.colorPrimary)
                transformations(BlurTransformation(view.context,1f,0.9f))
            }
        }
    }
}

@BindingAdapter("isGone")
fun bindGone(view:View, isGone:Boolean){
    view.isGone = isGone
}

/* search layout */
@BindingAdapter("isGoneLayout")
fun<T> bindingIsGoneLayout(view: View,results:LiveData<ApiResult<T>>){
    Timber.d("bindingIsGoneLayout ${view.id == R.id.recyclerView}  result : ${results.value}")
    if(results.value.isNotNull()) {
        when (results.value) {
            ApiResult.Loading, is ApiResult.Error -> {
                when(view.id){
                    R.id.shimmerLayout,
                    R.id.lv_search_album->view.isGone = false
                    else-> view.isGone = true
                }
            }
            is ApiResult.Success -> {
                when(view.id){
                    R.id.shimmerLayout,
                    R.id.lv_recent_search-> view.isGone = true
                    else-> view.isGone = false
                }
            }
        }
    }else {
        Timber.d("bindingIsGoneLayout false ")
        view.isGone = false
    }
}

@BindingAdapter("isGoneFavoriteLayout")
fun bindingIsGoneFavoriteLayout(view: View,results:LiveData<ApiResult<List<AlbumEntity>>>){
    Timber.d("bindingIsGoneFavoriteLayout : ${results.value}")
    when(view.id){
        R.id.recyclerView->{
            view.isGone = results.value.isSuccessAndNotNull()
        }
        else->{
            view.isGone = !results.value.isSuccessAndNotNull()
        }
    }
}

@BindingAdapter("isGoneMediaPlayer")
fun bindingIsGoneMediaPlayer(view: View,observerIsGone:ObservableBoolean){
    Timber.d("isGoneMediaPlayer : ${observerIsGone.get()}")
    view.isGone = when(view.id){
        R.id.cl_media_player-> !observerIsGone.get()
        else-> observerIsGone.get()
    }
}

@BindingAdapter("iconPlayPause")
fun iconPlayPause(view: ImageButton, state:MutableLiveData<MediaPlayerState>){
    view.apply {
        setImageResource(
            when(state.value){
                MediaPlayerState.PLAYING->R.drawable.ic_pause
                else->R.drawable.ic_play
            }
        )
        adjustViewBounds = true
        refreshDrawableState()
    }
}

@BindingAdapter("isGoneVolumeController")
fun bindingIsGoneVolumeController(view: View,observerIsGone:ObservableBoolean){
    Timber.d("isGoneMediaPlayer : ${observerIsGone.get()}")
    view.isGone = !observerIsGone.get()
}

@BindingAdapter("isNetworkError")
fun bindingIsNetworkError(view: View, observerIsNetwork:ObservableBoolean){
    Timber.d("isNetworkError : ${observerIsNetwork.get()}")
    view.isGone = observerIsNetwork.get()
}