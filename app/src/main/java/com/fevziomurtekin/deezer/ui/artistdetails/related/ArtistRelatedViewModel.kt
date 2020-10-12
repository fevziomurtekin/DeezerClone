package com.fevziomurtekin.deezer.ui.artistdetails.related

import android.accounts.NetworkErrorException
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.fevziomurtekin.deezer.core.Result
import com.fevziomurtekin.deezer.repository.DeezerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class ArtistRelatedViewModel @ViewModelInject constructor(
   private val mainRepository: DeezerRepository
) : ViewModel() {

   var  result : LiveData<Result<Any>> = MutableLiveData()
   var isNetworkError = MutableLiveData(false)


   init {
       Timber.d("viewmodel init...")
   }

   fun fetchArtistRelated(artistID:String){
      viewModelScope.launch {
         try {
            result = mainRepository.fetchArtistRelated(artistID)
               .asLiveData(viewModelScope.coroutineContext+ Dispatchers.Default)
         }catch (e:NetworkErrorException){
            isNetworkError.value = true
         }
      }
   }
}