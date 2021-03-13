package com.fevziomurtekin.deezer.ui.artist.details.related

import android.accounts.NetworkErrorException
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.asLiveData
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.data.ArtistRelatedData
import com.fevziomurtekin.deezer.ui.artist.ArtistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class ArtistRelatedViewModel @ViewModelInject constructor(
   private val repository: ArtistRepository
) : ViewModel() {

   var  result : LiveData<ApiResult<List<ArtistRelatedData>>> = MutableLiveData()
   var isNetworkError = MutableLiveData(false)

   fun fetchArtistRelated(artistID:String){
      viewModelScope.launch {
         try {
            result = repository.fetchArtistRelated(artistID)
               .asLiveData(viewModelScope.coroutineContext+ Dispatchers.Default)
         }catch (e:NetworkErrorException){
            isNetworkError.value = true
            Timber.e(e)
         }
      }
   }
}
