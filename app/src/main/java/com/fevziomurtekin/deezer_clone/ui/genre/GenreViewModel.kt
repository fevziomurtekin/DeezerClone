package com.fevziomurtekin.deezer_clone.ui.genre

import android.provider.ContactsContract
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.fevziomurtekin.deezer_clone.core.Result
import com.fevziomurtekin.deezer_clone.data.genre.Data
import com.fevziomurtekin.deezer_clone.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class GenreViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository
):ViewModel() {

    var result: LiveData<Result<Any>> = MutableLiveData()

    init {
        Timber.d("view model init.")
    }

    fun fetchResult(){
        viewModelScope.launch {
            result = mainRepository.fetchGenreList()
                .asLiveData(viewModelScope.coroutineContext+ Dispatchers.Default)
        }
    }

}