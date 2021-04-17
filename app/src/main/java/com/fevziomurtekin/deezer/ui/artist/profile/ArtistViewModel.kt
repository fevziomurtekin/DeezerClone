package com.fevziomurtekin.deezer.ui.artist.profile

import android.accounts.NetworkErrorException
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.data.ArtistData
import com.fevziomurtekin.deezer.ui.artist.ArtistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class ArtistViewModel @ViewModelInject constructor(
    private val repository: ArtistRepository
) : ViewModel(){

    var result: LiveData<ApiResult<List<ArtistData>>> = MutableLiveData()
    var isNetworkError = MutableLiveData(false)

    fun fetchResult(genreId:String){
        viewModelScope.launch {
            try {
                result = repository.fetchArtistList(genreId)
                    .asLiveData(viewModelScope.coroutineContext+ Dispatchers.Default)
            }catch (e:NetworkErrorException){
                isNetworkError.value = true
                Timber.e(e)
            }
        }
    }
}
