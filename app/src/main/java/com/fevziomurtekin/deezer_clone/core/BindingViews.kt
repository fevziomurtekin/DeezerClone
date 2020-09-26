package com.fevziomurtekin.deezer_clone.core

import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isGone
import androidx.databinding.BindingAdapter
import coil.Coil
import coil.load
import coil.transform.BlurTransformation
import coil.transform.CircleCropTransformation
import com.fevziomurtekin.deezer_clone.R
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.activity_main.view.*
import timber.log.Timber

/**
 * Help in the development of this class, the application named 'Pokedex' @Skydoves user has helped.
 * https://github.com/skydoves/Pokedex/blob/master/app/src/main/java/com/skydoves/pokedex/binding/ViewBinding.kt
 * */


/* Load to Image with url inside to layout files */
@BindingAdapter("bindImageUrl")
fun bindLoadImageUrl(view: ImageView, url: String) {
    Timber.d("Binding url : $url ")
    view.load(url){
        crossfade(true)
        placeholder(R.color.colorPrimary)
        transformations(BlurTransformation(view.context,6f,0.3f))
    }
}

@BindingAdapter("isGone")
fun bindGone(view:View, isGone:Boolean){
    view.isGone = isGone
}
