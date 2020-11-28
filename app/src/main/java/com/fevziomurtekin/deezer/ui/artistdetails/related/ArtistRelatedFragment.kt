package com.fevziomurtekin.deezer.ui.artistdetails.related

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.viewModels
import com.fevziomurtekin.deezer.R
import com.fevziomurtekin.deezer.core.ui.DataBindingFragment
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.core.extensions.UIExtensions
import com.fevziomurtekin.deezer.databinding.FragmentArtistRelatedBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_artist_related.*
import timber.log.Timber

@AndroidEntryPoint
class ArtistRelatedFragment(private val artistID:String)
    : DataBindingFragment() {

    private lateinit var binding: FragmentArtistRelatedBinding
    @VisibleForTesting
    val viewModel: ArtistRelatedViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = binding(inflater,R.layout.fragment_artist_related,container)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.apply {
            lifecycleOwner = this@ArtistRelatedFragment
            adapter = ArtistRelatedAdapter()
            vm = viewModel
        }

        viewModel.fetchArtistRelated(artistID)
        viewModel.result.observe(viewLifecycleOwner, {
            when(it){
                //TODO  progress dialog add.
                ApiResult.Loading->{ }
                is ApiResult.Error->{
                    UIExtensions.showSnackBar(this@ArtistRelatedFragment.lv_artist_related,this@ArtistRelatedFragment.getString(R.string.unexpected_error))
                    Timber.d("result : error isSplash : false")
                }
                is ApiResult.Success->{
                    Timber.d("result : succes isSplash : false")
                }
            }
        })
    }
}