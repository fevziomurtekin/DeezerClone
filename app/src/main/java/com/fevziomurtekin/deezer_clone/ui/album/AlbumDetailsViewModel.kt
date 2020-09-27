package com.fevziomurtekin.deezer_clone.ui.album

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.fevziomurtekin.deezer_clone.core.Result
import com.fevziomurtekin.deezer_clone.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class AlbumDetailsViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository
):ViewModel(){

    var result:LiveData<Result<Any>> = MutableLiveData()

    init {
        Timber.d("init...")
    }


    fun fetchingAlbumDatas(albumID:String){
        viewModelScope.launch {
            result = mainRepository.fetchAlbumTracks(albumID)
                .asLiveData(viewModelScope.coroutineContext+ Dispatchers.Default)
        }
    }
}