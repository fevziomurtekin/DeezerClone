package com.fevziomurtekin.deezer.ui.artist.details.albums

import android.accounts.NetworkErrorException
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.data.ArtistAlbumData
import com.fevziomurtekin.deezer.ui.artist.ArtistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class ArtistAlbumViewModel @ViewModelInject constructor(
    private val repository: ArtistRepository
): ViewModel(){

    var result: LiveData<ApiResult<List<ArtistAlbumData>>> = MutableLiveData()
    var isNetworkError = MutableLiveData(false)


    fun fetchArtistAlbum(artistID:String){
        viewModelScope.launch {
            try {
                result = repository.fetchArtistAlbums(artistID)
                    .asLiveData(viewModelScope.coroutineContext+ Dispatchers.Default)
            }catch (e:NetworkErrorException){
                isNetworkError.value = true
                Timber.e(e)
            }
        }
    }
}
