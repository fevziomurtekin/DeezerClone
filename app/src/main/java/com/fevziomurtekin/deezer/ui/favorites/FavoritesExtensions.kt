package com.fevziomurtekin.deezer.ui.favorites

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.core.extensions.isSuccessAndNotNull
import com.fevziomurtekin.deezer.entities.AlbumEntity
import timber.log.Timber

@BindingAdapter("adapterFavoritesList")
fun bindingFavoritesList(view: RecyclerView, results: LiveData<ApiResult<List<AlbumEntity>>>) {
    if (results.value.isSuccessAndNotNull()) {
        Timber.d("Favorites result :${results.value} ")
        if(results.value is ApiResult.Success)
            (view.adapter as FavoritesAdapter).addFavoritesList(
                (results.value as ApiResult.Success<List<AlbumEntity>>).data
            )
    }
}

