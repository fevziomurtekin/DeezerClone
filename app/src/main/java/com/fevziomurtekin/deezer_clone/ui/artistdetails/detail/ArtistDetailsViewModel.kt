package com.fevziomurtekin.deezer_clone.ui.artistdetails.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.fevziomurtekin.deezer_clone.core.Result
import com.fevziomurtekin.deezer_clone.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class ArtistDetailsViewModel @ViewModelInject constructor(
    val mainRepository: MainRepository
):ViewModel() {

    var result: LiveData<Result<Any>> = MutableLiveData()

    init {
        Timber.d("view model init.")
    }


    fun fetchArtistDetails(artistID:String){
        viewModelScope.launch {
            result = mainRepository.fetchArtistDetails(artistID)
                .asLiveData(viewModelScope.coroutineContext+ Dispatchers.Default)
        }
    }

}