package com.fevziomurtekin.deezer_clone.core

import android.view.View
import android.widget.ImageView
import androidx.core.view.isGone
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import coil.load
import coil.size.Scale
import coil.transform.BlurTransformation
import com.fevziomurtekin.deezer_clone.R
import com.fevziomurtekin.deezer_clone.data.artistdetails.ArtistDetailResponse
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
fun bindLoadImageArtistDetails(view: ImageView, results:LiveData<Result<Any>>) {
    when (results.value) {
        Result.Loading, Result.Error -> {/* Nothing */
        }
        is Result.Succes -> {
            val imgUrl = ((results.value as Result.Succes<ArtistDetailResponse>).data.picture_big)
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
fun bindingIsGoneLayout(view: View,results:LiveData<Result<Any>>){
    Timber.d("bindingIsGoneLayout ${view.id == R.id.recyclerView}  result : ${results.value}")
    if(results.value != null) {
        when (results.value) {
            Result.Loading, Result.Error -> {
                when(view.id){
                    R.id.shimmerLayout,
                    R.id.lv_search_album->view.isGone = false
                    else-> view.isGone = true
                }
            }
            is Result.Succes -> {
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

