package com.fevziomurtekin.deezer.ui.genre

import android.accounts.NetworkErrorException
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.data.Data
import com.fevziomurtekin.deezer.ui.main.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class GenreViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository
): ViewModel() {

    var result: LiveData<ApiResult<List<Data>?>> = MutableLiveData()
    var isNetworkError = MutableLiveData(false)

    fun fetchResult(){
        viewModelScope.launch {
            try {
                result = mainRepository.fetchGenreList()
                    .asLiveData(viewModelScope.coroutineContext + Dispatchers.Default)
            }catch (e:NetworkErrorException){
                isNetworkError.value = true
                Timber.e(e)
            }
        }
    }
}
