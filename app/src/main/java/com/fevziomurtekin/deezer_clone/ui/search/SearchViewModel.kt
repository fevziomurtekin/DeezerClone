package com.fevziomurtekin.deezer_clone.ui.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.fevziomurtekin.deezer_clone.data.search.SearchData
import com.fevziomurtekin.deezer_clone.data.search.SearchQuery
import com.fevziomurtekin.deezer_clone.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import com.fevziomurtekin.deezer_clone.core.Result
import kotlinx.coroutines.isActive


class SearchViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository
):ViewModel(){

    var result:LiveData<Result<Any>> = MutableLiveData()
    var recentSearch:LiveData<List<SearchQuery>> = MutableLiveData()

    init {
        Timber.d("viewmodel init...")
    }

    fun fetchingRecentSearch(){
        viewModelScope.launch {
            recentSearch = mainRepository.fetchRecentSearch()
                    .asLiveData(viewModelScope.coroutineContext+ Dispatchers.Default)

        }
    }

    fun fetchSearch(query:String){
        Timber.d("fetch data q : $query scope :${viewModelScope.isActive}")
        viewModelScope.launch {
            Timber.d("fetchdata viewModelScope")
            result = mainRepository.fetchSearch(query)
                    .asLiveData(viewModelScope.coroutineContext+ Dispatchers.Default)
        }
    }


}