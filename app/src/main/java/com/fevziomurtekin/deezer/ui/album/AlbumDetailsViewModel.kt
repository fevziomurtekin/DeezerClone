package com.fevziomurtekin.deezer.ui.album

import android.accounts.NetworkErrorException
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.core.mapper.mapper
import com.fevziomurtekin.deezer.data.AlbumData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class AlbumDetailsViewModel @ViewModelInject constructor(
    private val repository: AlbumRepository
): ViewModel(){

    var result: LiveData<ApiResult<List<AlbumData>>> = MutableLiveData()
    var isNetworkError = MutableLiveData(false)


    fun favoritedToTrack(data:AlbumData) {
        viewModelScope.launch {
            repository.insertFavoritesData(track = data.mapper())
        }
    }

    fun fetchingAlbumDatas(albumID:String){
        viewModelScope.launch {
            try{
                result = repository.fetchAlbumTracks(albumID)
                    .asLiveData(viewModelScope.coroutineContext+ Dispatchers.Default)
            }catch (e:NetworkErrorException){
                isNetworkError.value = true
                Timber.e(e)
            }
        }
    }
}
