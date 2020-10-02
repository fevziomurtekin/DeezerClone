package com.fevziomurtekin.deezer_clone.ui.favorites

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.fevziomurtekin.deezer_clone.data.albumdetails.AlbumData
import com.fevziomurtekin.deezer_clone.data.search.SearchQuery
import com.fevziomurtekin.deezer_clone.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class FavoritesViewModel @ViewModelInject constructor(
        private val mainRepository: MainRepository
):ViewModel() {

    var favorites:LiveData<List<AlbumData>> = MutableLiveData()

    init {
        Timber.d("init viewmodel...")
    }

    fun fetchFavorites(){
        Timber.d("fetchFavorites")
        viewModelScope.launch {
            favorites = mainRepository.fetchFavorites()
                .asLiveData(viewModelScope.coroutineContext+ Dispatchers.Default)
        }
    }

}