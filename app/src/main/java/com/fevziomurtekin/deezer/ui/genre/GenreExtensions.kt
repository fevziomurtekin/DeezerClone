package com.fevziomurtekin.deezer.ui.genre

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.data.Data
import timber.log.Timber

@BindingAdapter("adapterGenreList")
fun bindingGenreList(view: RecyclerView, results: LiveData<ApiResult<List<Data>>>) {
    when (results.value) {
        ApiResult.Loading, is ApiResult.Error -> {/* Nothing */ }
        is ApiResult.Success -> {
            Timber.d("result : succes isSplash : false")
            (view.adapter as GenreAdapter).addGenreList((((results.value as ApiResult.Success<List<Data>>).data)
                    as List<Data>))
        }
    }
}
