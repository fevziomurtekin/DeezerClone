package com.fevziomurtekin.deezer_clone.ui.artist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.fevziomurtekin.deezer_clone.core.Result
import com.fevziomurtekin.deezer_clone.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class ArtistViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository
) : ViewModel(){

    var result: LiveData<Result<Any>> = MutableLiveData()

    init {
        Timber.d("view model init.")
    }

    fun fetchResult(genreId:String){
        viewModelScope.launch {
            result = mainRepository.fetchArtistList(genreId)
                .asLiveData(viewModelScope.coroutineContext+ Dispatchers.Default)
        }
    }

}