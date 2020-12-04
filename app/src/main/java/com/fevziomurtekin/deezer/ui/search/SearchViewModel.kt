package com.fevziomurtekin.deezer.ui.search

import android.accounts.NetworkErrorException
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.entities.SearchEntity
import com.fevziomurtekin.deezer.repository.DeezerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber


class SearchViewModel @ViewModelInject constructor(
    private val mainRepository: DeezerRepository
):ViewModel(){

    var queryLiveData:MutableLiveData<String> = MutableLiveData()
    var result:LiveData<ApiResult<Any>> = MutableLiveData()
    var recentSearch:LiveData<ApiResult<List<SearchEntity>>> = MutableLiveData()
    var isNetworkError = MutableLiveData(false)
    val editorActionListener:TextView.OnEditorActionListener

    init {
        this.editorActionListener = TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                Timber.d("action search")
                queryLiveData.value = v.text.toString()
                true
            } else false
        }


    }

    fun fetchingRecentSearch(){
        viewModelScope.launch {
            recentSearch = mainRepository.fetchRecentSearch()
                    .asLiveData(viewModelScope.coroutineContext+ Dispatchers.Default)

        }
    }

    fun fetchSearch(){
        viewModelScope.launch {
            /* Each query update enter to switchmap */
                try{
                    result = queryLiveData.switchMap { q ->
                        mainRepository.fetchSearch(q)
                            .asLiveData(viewModelScope.coroutineContext + Dispatchers.Main)
                    }
                }catch (e:NetworkErrorException){
                    isNetworkError.value = true
                }
        }
    }

}