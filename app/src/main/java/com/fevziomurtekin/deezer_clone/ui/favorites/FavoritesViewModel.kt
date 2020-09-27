package com.fevziomurtekin.deezer_clone.ui.favorites

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fevziomurtekin.deezer_clone.repository.MainRepository
import timber.log.Timber

class FavoritesViewModel @ViewModelInject constructor(
        private val mainRepository: MainRepository
):ViewModel() {

    val result:LiveData<Result<Any>> = MutableLiveData()

    init {
        Timber.d("init viewmodel...")
    }

    fun fetchFavorites(){}

}