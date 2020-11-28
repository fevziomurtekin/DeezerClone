package com.fevziomurtekin.deezer.core

import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.core.extensions.isSuccessAndNotNull
import com.fevziomurtekin.deezer.data.albumdetails.AlbumData
import com.fevziomurtekin.deezer.data.artist.ArtistData
import com.fevziomurtekin.deezer.data.artistdetails.ArtistAlbumData
import com.fevziomurtekin.deezer.data.artistdetails.ArtistRelatedData
import com.fevziomurtekin.deezer.data.genre.Data
import com.fevziomurtekin.deezer.data.search.SearchData
import com.fevziomurtekin.deezer.entities.AlbumEntity
import com.fevziomurtekin.deezer.entities.SearchEntity
import com.fevziomurtekin.deezer.ui.album.AlbumDetailsAdapter
import com.fevziomurtekin.deezer.ui.artist.ArtistAdapter
import com.fevziomurtekin.deezer.ui.artistdetails.albums.ArtistAlbumAdapter
import com.fevziomurtekin.deezer.ui.artistdetails.related.ArtistRelatedAdapter
import com.fevziomurtekin.deezer.ui.favorites.FavoritesAdapter
import com.fevziomurtekin.deezer.ui.genre.GenreAdapter
import com.fevziomurtekin.deezer.ui.search.RecentSearchAdapter
import com.fevziomurtekin.deezer.ui.search.SearchAlbumAdapter
import com.google.android.material.tabs.TabLayout
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
fun bindingGenreList(view:RecyclerView, results:LiveData<ApiResult<List<Data>>>) {
    when (results.value) {
        ApiResult.Loading, is ApiResult.Error -> {/* Nothing */ }
        is ApiResult.Success -> {
            Timber.d("result : succes isSplash : false")
            (view.adapter as GenreAdapter).addGenreList((((results.value as ApiResult.Success<List<Data>>).data)
                    as List<Data>))
        }
    }
}

@BindingAdapter("adapterArtistList")
fun bindingArtistList(view:RecyclerView, results:LiveData<ApiResult<List<ArtistData>>>) {
    when (results.value) {
        ApiResult.Loading, is ApiResult.Error -> {/* Nothing */ }
        is ApiResult.Success -> {
            Timber.d("result : succes isSplash : false")
            (view.adapter as ArtistAdapter).addArtistList((((results.value as ApiResult.Success<List<ArtistData>>).data)
                    as ApiResult.Success<List<ArtistData>>).data)
        }
    }
}

@BindingAdapter("adapterTablayout")
fun bindingTabLayoutAdapter(view:TabLayout,viewPager: ViewPager){
    view.setupWithViewPager(viewPager)
}

@BindingAdapter("adapterViewPager")
fun bindingViewPagerAdapter(view:ViewPager,adapter:FragmentPagerAdapter){
    view.adapter = adapter
}


@BindingAdapter("adapterAAlbumsList")
fun bindingAAlbumsList(view:RecyclerView, results:LiveData<ApiResult<Any>>) {
    when (results.value) {
        ApiResult.Loading, is ApiResult.Error -> {/* Nothing */ }
        is ApiResult.Success -> {
            Timber.d("result : succes isSplash : false")
            (view.adapter as ArtistAlbumAdapter).addAlbumList((((results.value as ApiResult.Success<Any>).data) as ApiResult.Success<List<ArtistAlbumData>>).data)
        }
    }
}

@BindingAdapter("adapterARelatedList")
fun bindingARelatedList(view:RecyclerView, results:LiveData<ApiResult<Any>>) {
    when (results.value) {
        ApiResult.Loading, is ApiResult.Error -> {/* Nothing */ }
        is ApiResult.Success -> {
            Timber.d("result : succes isSplash : false")
            (view.adapter as ArtistRelatedAdapter).addRelatedList((((results.value as ApiResult.Success<Any>)
                .data) as ApiResult.Success<List<ArtistRelatedData>>).data)
        }
    }
}

@BindingAdapter("adapterAlbumTracksList")
fun bindingAlbumTracksList(view:RecyclerView, results:LiveData<ApiResult<Any>>) {
    when (results.value) {
        ApiResult.Loading, is ApiResult.Error -> {/* Nothing */ }
        is ApiResult.Success -> {
            Timber.d("result : succes isSplash : false")
            (view.adapter as AlbumDetailsAdapter)
                .addAlbumTracks((((results.value as ApiResult.Success<Any>).data)
                        as ApiResult.Success<List<AlbumData>>).data)
        }
    }
}

@BindingAdapter("adapterRecentSearch")
fun bindingRecentSeach(view:RecyclerView, results:LiveData<ApiResult<List<SearchEntity>>>) {
    Timber.d("binding recentData : ${results.value}")
    when(results.value){
        ApiResult.Loading, is ApiResult.Error-> {/* Nothing */ }
        is ApiResult.Success -> {
            (view.adapter as RecentSearchAdapter)
                .addRecentSearch(
                    (((results.value as ApiResult.Success<List<SearchEntity>>).data)
                            as List<SearchEntity>))
        }
    }
}

@BindingAdapter("adapterSearchAlbum")
fun bindingSearchAlbum(view:RecyclerView, results:LiveData<ApiResult<Any>>) {
    when (results.value) {
        ApiResult.Loading, is ApiResult.Error -> {/* Nothing */ }
        is ApiResult.Success -> {
            Timber.d("adapterSearchAlbum")
            (view.adapter as SearchAlbumAdapter).addAlbumSearch(((results.value) as ApiResult.Success<List<SearchData>>).data)
        }
    }
}

@BindingAdapter("onEditorActionListener")
fun bindOnEditorActionListener(editText: EditText, editorActionListener: TextView.OnEditorActionListener) {
    editText.setOnEditorActionListener(editorActionListener)
}

@BindingAdapter("adapterFavoritesList")
fun bindingFavoritesList(view:RecyclerView, results:LiveData<ApiResult<List<AlbumEntity>>>) {
    if (results.value.isSuccessAndNotNull()) {
        Timber.d("Favorites result :${results.value} ")
        if(results.value is ApiResult.Success)
            (view.adapter as FavoritesAdapter).addFavoritesList((results.value as ApiResult.Success<List<AlbumEntity>>).data)
    }
}