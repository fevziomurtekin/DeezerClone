package com.fevziomurtekin.deezer.ui.genre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.viewModels
import com.fevziomurtekin.deezer.R
import com.fevziomurtekin.deezer.core.DataBindingFragment
import com.fevziomurtekin.deezer.core.Result
import com.fevziomurtekin.deezer.core.UIExtensions
import com.fevziomurtekin.deezer.databinding.ActivityMainBinding
import com.fevziomurtekin.deezer.databinding.FragmentGenreBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_genre.*
import timber.log.Timber

@AndroidEntryPoint
class GenreFragment : DataBindingFragment() {

    private lateinit var  binding:FragmentGenreBinding
    @VisibleForTesting
    val viewModel : GenreViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = binding(inflater,R.layout.fragment_genre,container)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.apply {
            lifecycleOwner = this@GenreFragment
            adapter = GenreAdapter()
            vm = viewModel
        }

        viewModel.fetchResult()
        viewModel.result.observe(viewLifecycleOwner,{
            //Timber.d("result:${it}")
            when(it){
                //TODO  progress dialog add.
                Result.Loading->{ }
                Result.Error->{
                    UIExtensions.showSnackBar(this@GenreFragment.constraint_main,this@GenreFragment.getString(R.string.unexpected_error))
                    Timber.d("result : error isSplash : false")
                }
                is Result.Succes->{
                    Timber.d("result : succes isSplash : false")
                }
            }

        })

        viewModel.isNetworkError.observe(viewLifecycleOwner,{
            if(it){
                UIExtensions.showSnackBar(this@GenreFragment.cl_genre,this@GenreFragment.getString(R.string.network_error))
            }
        })

    }
}