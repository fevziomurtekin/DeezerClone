package com.fevziomurtekin.deezer_clone.ui.artistdetails.related

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.fevziomurtekin.deezer_clone.core.Result
import com.fevziomurtekin.deezer_clone.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class ArtistRelatedViewModel @ViewModelInject constructor(
   private val mainRepository: MainRepository
) : ViewModel() {

   var  result : LiveData<Result<Any>> = MutableLiveData()


   init {
       Timber.d("viewmodel init...")
   }

   fun fetchArtistRelated(artistID:String){
      viewModelScope.launch {
         result = mainRepository.fetchArtistRelated(artistID)
            .asLiveData(viewModelScope.coroutineContext+ Dispatchers.Default)
      }
   }
}