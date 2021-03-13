package com.fevziomurtekin.deezer.ui.album

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.data.AlbumData
import com.fevziomurtekin.deezer.data.ArtistAlbumData
import com.fevziomurtekin.deezer.data.ArtistRelatedData
import com.fevziomurtekin.deezer.ui.artist.details.albums.ArtistAlbumAdapter
import com.fevziomurtekin.deezer.ui.artist.details.related.ArtistRelatedAdapter
import timber.log.Timber

@BindingAdapter("adapterAAlbumsList")
fun bindingAAlbumsList(view: RecyclerView, results: LiveData<ApiResult<List<ArtistAlbumData>>>) {
    when (results.value) {
        ApiResult.Loading, is ApiResult.Error -> {/* Nothing */ }
        is ApiResult.Success -> {
            (view.adapter as ArtistAlbumAdapter)
                .addAlbumList(
                    (results.value as ApiResult.Success<List<ArtistAlbumData>>).data
                )
        }
    }
}

@BindingAdapter("adapterARelatedList")
fun bindingARelatedList(view: RecyclerView, results: LiveData<ApiResult<List<ArtistRelatedData>>>) {
    when (results.value) {
        ApiResult.Loading, is ApiResult.Error -> {/* Nothing */ }
        is ApiResult.Success -> {
            Timber.d("result : succes isSplash : false")
            (view.adapter as ArtistRelatedAdapter)
                .addRelatedList(
                    (results.value as ApiResult.Success<List<ArtistRelatedData>>).data
                )
        }
    }
}

@BindingAdapter("adapterAlbumTracksList")
fun bindingAlbumTracksList(view: RecyclerView, results: LiveData<ApiResult<List<AlbumData>>>) {
    when (results.value) {
        ApiResult.Loading, is ApiResult.Error -> {/* Nothing */ }
        is ApiResult.Success -> {
            Timber.d("result : succes isSplash : false")
            (view.adapter as AlbumDetailsAdapter)
                .addAlbumTracks(
                    (results.value as ApiResult.Success<List<AlbumData>>).data
                )
        }
    }
}
