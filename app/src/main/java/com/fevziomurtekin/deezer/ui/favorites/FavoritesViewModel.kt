package com.fevziomurtekin.deezer.ui.favorites

import android.accounts.NetworkErrorException
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.entities.AlbumEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class FavoritesViewModel @ViewModelInject constructor(
        private val favoritesRepository: FavoritesRepository
): ViewModel() {

    var favorites: LiveData<ApiResult<List<AlbumEntity>>> = MutableLiveData()
    var isNetworkError = MutableLiveData(false)

    fun fetchFavorites(){
        viewModelScope.launch {
            try {
                favorites = favoritesRepository.fetchFavorites()
                    .asLiveData(viewModelScope.coroutineContext+ Dispatchers.Default)
            } catch (e:NetworkErrorException){
                isNetworkError.value = true
                Timber.e(e)
            }
        }
    }
}
