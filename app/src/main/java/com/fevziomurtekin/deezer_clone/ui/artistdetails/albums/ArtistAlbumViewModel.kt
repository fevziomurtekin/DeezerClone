package com.fevziomurtekin.deezer_clone.ui.artistdetails.albums

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.fevziomurtekin.deezer_clone.core.Result
import com.fevziomurtekin.deezer_clone.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class ArtistAlbumViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository
):ViewModel(){

    var result:LiveData<Result<Any>> = MutableLiveData()

    init {
        Timber.d("init viewModel..")
    }

    fun fetchArtistAlbum(artistID:String){
        viewModelScope.launch {
            result = mainRepository.fetchArtistAlbums(artistID)
                .asLiveData(viewModelScope.coroutineContext+ Dispatchers.Default)
        }
    }

}