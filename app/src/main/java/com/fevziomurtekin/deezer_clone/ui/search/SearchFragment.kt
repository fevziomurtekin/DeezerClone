package com.fevziomurtekin.deezer_clone.ui.search

import android.os.Bundle
import android.text.Editable
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import android.widget.TextView
import androidx.annotation.VisibleForTesting
import androidx.core.text.toSpanned
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.fevziomurtekin.deezer_clone.R
import com.fevziomurtekin.deezer_clone.core.DataBindingFragment
import com.fevziomurtekin.deezer_clone.core.UIExtensions
import com.fevziomurtekin.deezer_clone.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.runBlocking
import timber.log.Timber

@AndroidEntryPoint
class SearchFragment: DataBindingFragment() {

    @VisibleForTesting
    val viewModel: SearchViewModel by viewModels()
    lateinit var binding: FragmentSearchBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = binding(inflater, R.layout.fragment_search, container)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.apply {
            lifecycleOwner = this@SearchFragment
            recentAdapter = RecentSearchAdapter(object : RecentSearchAdapter.RecentSearchListener{
                override fun recentSearchListener(query: String) {
                    aetSearch.text = Editable.Factory.getInstance().newEditable(query)
                    viewModel.queryLiveData.value = query
                }
            })
            searchAdapter = SearchAlbumAdapter()
            vm = viewModel
        }

        viewModel.fetchingRecentSearch()
        viewModel.fetchSearch()

    }

}