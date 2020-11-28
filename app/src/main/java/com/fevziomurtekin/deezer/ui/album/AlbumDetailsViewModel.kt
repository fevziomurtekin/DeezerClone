package com.fevziomurtekin.deezer.ui.album

import android.accounts.NetworkErrorException
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.core.mapper
import com.fevziomurtekin.deezer.data.albumdetails.AlbumData
import com.fevziomurtekin.deezer.repository.DeezerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class AlbumDetailsViewModel @ViewModelInject constructor(
    private val mainRepository: DeezerRepository
):ViewModel(){

    var result:LiveData<ApiResult<Any>> = MutableLiveData()
    var isNetworkError = MutableLiveData(false)

    init {
        Timber.d("init...")
    }

    fun favoritedToTrack(data:AlbumData) {
        viewModelScope.launch {
            mainRepository.insertFavoritesData(track = data.mapper())
        }
    }

    fun fetchingAlbumDatas(albumID:String){
        viewModelScope.launch {
            try{
                result = mainRepository.fetchAlbumTracks(albumID)
                    .asLiveData(viewModelScope.coroutineContext+ Dispatchers.Default)
            }catch (e:NetworkErrorException){
                isNetworkError.value = true
            }
        }
    }
}