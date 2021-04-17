package com.fevziomurtekin.deezer.ui.genre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.fevziomurtekin.deezer.R
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.core.extensions.UIExtensions
import com.fevziomurtekin.deezer.core.ui.DataBindingFragment
import com.fevziomurtekin.deezer.data.Data
import com.fevziomurtekin.deezer.databinding.FragmentGenreBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_genre.*
import timber.log.Timber

@AndroidEntryPoint
class GenreFragment : DataBindingFragment() {

    lateinit var  binding:FragmentGenreBinding
    @VisibleForTesting
    val viewModel : GenreViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = binding(inflater,R.layout.fragment_genre,container)
        return binding.root
    }

    override fun getSafeArgs() = Unit

    override fun initBinding() {
        binding.apply {
            lifecycleOwner = this@GenreFragment
            adapter = GenreAdapter(::onGenreItemClick)
            vm = viewModel
        }
    }

    override fun setListeners() = Unit

    override fun observeLiveData() {
        viewModel.fetchResult()
        viewModel.result.observe(viewLifecycleOwner,{
            when(it){
                ApiResult.Loading->{ }
                is ApiResult.Error->{
                    UIExtensions
                        .showSnackBar(
                            this@GenreFragment.constraint_main,
                            this@GenreFragment.getString(R.string.unexpected_error
                            ))
                    Timber.d("result : error isSplash : false")
                }
                is ApiResult.Success->{
                    Timber.d("result : succes isSplash : false")
                }
            }

        })

        viewModel.isNetworkError.observe(viewLifecycleOwner,{
            if(it){
                UIExtensions.showSnackBar(
                    this@GenreFragment.cl_genre,
                    this@GenreFragment.getString(R.string.network_error
                    ))
            }
        })
    }

    private fun onGenreItemClick(genreData: Data) {
     GenreFragmentDirections.actionGenreList(
         genreData.id,
         genreData.name.orEmpty()
     ).let { actionGenreList ->
         findNavController().navigate(actionGenreList)
     }
    }
}

