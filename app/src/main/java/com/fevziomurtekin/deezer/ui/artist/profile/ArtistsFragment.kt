package com.fevziomurtekin.deezer.ui.artist.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.fevziomurtekin.deezer.R
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.core.extensions.UIExtensions
import com.fevziomurtekin.deezer.core.ui.DataBindingFragment
import com.fevziomurtekin.deezer.data.ArtistData
import com.fevziomurtekin.deezer.databinding.FragmentArtistsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_artists.*

@AndroidEntryPoint
class ArtistsFragment : DataBindingFragment(){

    private lateinit var binding:FragmentArtistsBinding
    @VisibleForTesting
    val viewModel: ArtistViewModel by viewModels()
    private val args: ArtistsFragmentArgs by navArgs()
    private var id:String = "0" //default value.


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = binding(inflater,R.layout.fragment_artists,container)
        return binding.root
    }

    override fun getSafeArgs() { id = args.id }

    override fun initBinding() {
        binding.apply {
            lifecycleOwner = this@ArtistsFragment
            adapter = ArtistAdapter(::onClickArtistItem)
            vm = viewModel
        }
    }

    override fun setListeners() = Unit

    override fun observeLiveData() {
        viewModel.fetchResult(id)
        viewModel.result.observe(viewLifecycleOwner, {
            when(it){
                ApiResult.Loading->{ }
                is ApiResult.Error->{
                    UIExtensions.showSnackBar(
                        this@ArtistsFragment.lv_main,
                        this@ArtistsFragment.getString(R.string.unexpected_error))
                }
                is ApiResult.Success-> Unit
            }
        })
    }

    private fun onClickArtistItem(data: ArtistData) {
        ArtistsFragmentDirections.actionArtistDetails(
            data.id, data.name
        ).let { actionArtistDetails ->
            findNavController().navigate(actionArtistDetails)
        }
    }
}
