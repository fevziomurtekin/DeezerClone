package com.fevziomurtekin.deezer.ui.artist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.viewModels
import com.fevziomurtekin.deezer.R
import com.fevziomurtekin.deezer.core.DataBindingFragment
import com.fevziomurtekin.deezer.core.Env
import com.fevziomurtekin.deezer.core.UIExtensions
import com.fevziomurtekin.deezer.databinding.FragmentArtistsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_artists.*
import timber.log.Timber
import com.fevziomurtekin.deezer.core.Result

@AndroidEntryPoint
class ArtistsFragment : DataBindingFragment(){

    private lateinit var binding:FragmentArtistsBinding
    @VisibleForTesting
    val viewModel: ArtistViewModel by viewModels()
    var id:String = "0" //default value.


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = binding(inflater,R.layout.fragment_artists,container)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            id = it.getString(Env.BUND_ID).let {s->
                if(s.isNullOrEmpty()) "0" else s
            }
        }

        Timber.d("artist list - genreId : $id")

        binding.apply {
            lifecycleOwner = this@ArtistsFragment
            adapter = ArtistAdapter()
            vm = viewModel
        }

        viewModel.fetchResult(id)
        viewModel.result.observe(viewLifecycleOwner, {
            when(it){
                //TODO  progress dialog add.
                Result.Loading->{ }
                Result.Error->{
                    UIExtensions.showSnackBar(this@ArtistsFragment.lv_main,this@ArtistsFragment.getString(R.string.unexpected_error))
                    Timber.d("result : error isSplash : false")
                }
                is Result.Succes->{
                    Timber.d("result : succes isSplash : false")
                }
            }
        })

    }
}