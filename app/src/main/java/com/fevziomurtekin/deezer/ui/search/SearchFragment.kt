package com.fevziomurtekin.deezer.ui.search

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.viewModels
import com.fevziomurtekin.deezer.R
import com.fevziomurtekin.deezer.core.extensions.UIExtensions
import com.fevziomurtekin.deezer.core.ui.DataBindingFragment
import com.fevziomurtekin.deezer.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search.*

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

        viewModel.isNetworkError.observe(viewLifecycleOwner,{
            if(it){
                UIExtensions.showSnackBar(this@SearchFragment.cl_search,this@SearchFragment.getString(R.string.network_error))
            }
        })
    }

}