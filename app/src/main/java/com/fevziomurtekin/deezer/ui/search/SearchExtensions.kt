package com.fevziomurtekin.deezer.ui.search

import android.view.inputmethod.EditorInfo
import android.widget.TextView

internal fun SearchViewModel.initSearchActionListener() {
    editorActionListener = TextView.OnEditorActionListener { v, actionId, event ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            queryLiveData.value = v.text.toString()
            true
        } else false
    }
}