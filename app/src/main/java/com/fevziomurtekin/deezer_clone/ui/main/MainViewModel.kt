package com.fevziomurtekin.deezer_clone.ui.main

import androidx.annotation.MainThread
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber


class MainViewModel @ViewModelInject constructor(
): ViewModel(){



    init {
        Timber.d("init mainViewModel")
        viewModelScope.launch {

        }
    }


}