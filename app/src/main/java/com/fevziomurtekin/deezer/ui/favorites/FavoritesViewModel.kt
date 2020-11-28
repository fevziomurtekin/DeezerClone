package com.fevziomurtekin.deezer.ui.favorites

import android.accounts.NetworkErrorException
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.data.albumdetails.AlbumData
import com.fevziomurtekin.deezer.entities.AlbumEntity
import com.fevziomurtekin.deezer.repository.DeezerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class FavoritesViewModel @ViewModelInject constructor(
        private val mainRepository: DeezerRepository
):ViewModel() {

    var favorites:LiveData<ApiResult<List<AlbumEntity>>> = MutableLiveData()
    var isNetworkError = MutableLiveData(false)

    init {
        Timber.d("init viewmodel...")
    }

    fun fetchFavorites(){
        Timber.d("fetchFavorites")
        viewModelScope.launch {
            try {
                favorites = mainRepository.fetchFavorites()
                    .asLiveData(viewModelScope.coroutineContext+ Dispatchers.Default)
            }catch (e:NetworkErrorException){
                isNetworkError.value = true
            }
        }
    }

}