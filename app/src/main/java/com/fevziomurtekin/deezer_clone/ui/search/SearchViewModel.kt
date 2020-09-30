package com.fevziomurtekin.deezer_clone.ui.search

import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.fevziomurtekin.deezer_clone.data.search.SearchData
import com.fevziomurtekin.deezer_clone.data.search.SearchQuery
import com.fevziomurtekin.deezer_clone.repository.MainRepository
import timber.log.Timber
import com.fevziomurtekin.deezer_clone.core.Result
import com.fevziomurtekin.deezer_clone.core.UIExtensions
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.*


class SearchViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository
):ViewModel(){

    var queryLiveData:MutableLiveData<String> = MutableLiveData()
    var result:LiveData<Result<Any>> = MutableLiveData()
    var recentSearch:LiveData<List<SearchQuery>> = MutableLiveData()
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
            result = queryLiveData.switchMap { q->
                 mainRepository.fetchSearch(q)
                        .asLiveData(viewModelScope.coroutineContext + Dispatchers.Main)
            }
        }
    }

}