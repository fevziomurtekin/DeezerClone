package com.fevziomurtekin.deezer_clone.core

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.fevziomurtekin.deezer_clone.R
import com.fevziomurtekin.deezer_clone.data.artist.ArtistData
import com.fevziomurtekin.deezer_clone.data.genre.Data
import com.fevziomurtekin.deezer_clone.ui.artist.ArtistAdapter
import com.fevziomurtekin.deezer_clone.ui.genre.GenreAdapter
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

/**
 * Help in the development of this class, the application named 'Pokedex' @Skydoves user has helped.
 * https://github.com/skydoves/Pokedex/blob/master/app/src/main/java/com/skydoves/pokedex/binding/RecyclerViewBinding.kt
 * */

@BindingAdapter("adapter")
fun bindAdapter(view:RecyclerView, adapter:RecyclerView.Adapter<*>){
    view.adapter = adapter
}

@BindingAdapter("adapterGenreList")
fun bindingGenreList(view:RecyclerView, results:LiveData<Result<Any>>) {
    when (results.value) {
        Result.Loading, Result.Error -> {/* Nothing */
        }
        is Result.Succes -> {
            Timber.d("result : succes isSplash : false")
            (view.adapter as GenreAdapter).addGenreList(((results.value) as Result.Succes<List<Data>>).data)
        }
    }
}

@BindingAdapter("adapterArtistList")
fun bindingArtistList(view:RecyclerView, results:LiveData<Result<Any>>) {
    when (results.value) {
        Result.Loading, Result.Error -> {/* Nothing */
        }
        is Result.Succes -> {
            Timber.d("result : succes isSplash : false")
            (view.adapter as ArtistAdapter).addArtistList(((results.value) as Result.Succes<List<ArtistData>>).data)
        }
    }
}



