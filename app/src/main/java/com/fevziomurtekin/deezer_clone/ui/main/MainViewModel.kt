package com.fevziomurtekin.deezer_clone.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.fevziomurtekin.deezer_clone.core.Result
import com.fevziomurtekin.deezer_clone.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber


class MainViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository
): ViewModel(){

    var genreListLiveData: LiveData<Result<Any>> = MutableLiveData()
    var isSplash:MutableLiveData<Boolean> = MutableLiveData()

    init {
        Timber.d("init mainViewModel")
        isSplash.value = true
    }

    fun fetchGenreList(){
        viewModelScope.launch {
            genreListLiveData = mainRepository.fetchGenreList()
                .asLiveData(viewModelScope.coroutineContext+Dispatchers.Default)
        }
    }



}