package com.fevziomurtekin.deezer.ui.artist.details.info

import android.accounts.NetworkErrorException
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.asLiveData
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.data.ArtistDetailResponse
import com.fevziomurtekin.deezer.ui.artist.ArtistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class ArtistDetailsViewModel @ViewModelInject constructor(
    val repository: ArtistRepository
): ViewModel() {

    var result: LiveData<ApiResult<ArtistDetailResponse>> = MutableLiveData()
    var isNetworkError = MutableLiveData(false)


    fun fetchArtistDetails(artistID:String){
        viewModelScope.launch {
            try {
                result = repository.fetchArtistDetails(artistID)
                    .asLiveData(viewModelScope.coroutineContext+ Dispatchers.Default)
            }catch (e:NetworkErrorException){
                isNetworkError.value = true
                Timber.e(e)
            }
        }
    }
}
