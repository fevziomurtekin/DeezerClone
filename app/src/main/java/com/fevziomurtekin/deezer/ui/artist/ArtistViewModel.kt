package com.fevziomurtekin.deezer.ui.artist

import android.accounts.NetworkErrorException
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.data.artist.ArtistData
import com.fevziomurtekin.deezer.repository.DeezerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class ArtistViewModel @ViewModelInject constructor(
    private val mainRepository: DeezerRepository
) : ViewModel(){

    var result: LiveData<ApiResult<List<ArtistData>?>> = MutableLiveData()
    var isNetworkError = MutableLiveData(false)

    init {
        Timber.d("view model init.")
    }

    fun fetchResult(genreId:String){
        viewModelScope.launch {
            try {
                result = mainRepository.fetchArtistList(genreId)
                    .asLiveData(viewModelScope.coroutineContext+ Dispatchers.Default)
            }catch (e:NetworkErrorException){
                isNetworkError.value = true
            }
        }
    }

}