package com.fevziomurtekin.deezer.ui.search

import android.accounts.NetworkErrorException
import android.widget.TextView
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.entities.SearchEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber


class SearchViewModel @ViewModelInject constructor(
    private val repository: SearchRepository
): ViewModel(){

    var queryLiveData: MutableLiveData<String> = MutableLiveData()
    var result: LiveData<ApiResult<Any>> = MutableLiveData()
    var recentSearch:LiveData<ApiResult<List<SearchEntity>>> = MutableLiveData()
    var isNetworkError = MutableLiveData(false)
    lateinit var editorActionListener:TextView.OnEditorActionListener

    init {
        initSearchActionListener()
    }

    fun fetchingRecentSearch(){
        viewModelScope.launch {
            recentSearch = repository.fetchRecentSearch()
                    .asLiveData(viewModelScope.coroutineContext+ Dispatchers.Default)

        }
    }

    /**
     * Each query update enter to switchmap
     */
    fun fetchSearch(){
        viewModelScope.launch {
                try{
                    result = queryLiveData.switchMap { q ->
                        val asLiveData: LiveData<ApiResult<Any>> = repository.fetchSearch(q)
                            .asLiveData(viewModelScope.coroutineContext + Dispatchers.Main)
                        asLiveData
                    }
                }catch (e:NetworkErrorException){
                    isNetworkError.value = true
                    Timber.e(e)
                }
        }
    }
}
