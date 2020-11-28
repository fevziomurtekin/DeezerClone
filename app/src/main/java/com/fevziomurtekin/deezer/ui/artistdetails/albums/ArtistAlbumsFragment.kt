package com.fevziomurtekin.deezer.ui.artistdetails.albums

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
import com.fevziomurtekin.deezer.databinding.FragmentArtistAlbumsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_artist_albums.*
import timber.log.Timber

@AndroidEntryPoint
class ArtistAlbumsFragment(private val artistID:String): DataBindingFragment() {

    @VisibleForTesting val viewModel:ArtistAlbumViewModel by viewModels()
    private lateinit var binding: FragmentArtistAlbumsBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = binding(inflater,R.layout.fragment_artist_albums,container)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.apply {
            lifecycleOwner = this@ArtistAlbumsFragment
            adapter = ArtistAlbumAdapter()
            vm = viewModel
        }

        viewModel.fetchArtistAlbum(artistID)
        viewModel.result.observe(viewLifecycleOwner, {
            when(it){
                //TODO  progress dialog add.
                ApiResult.Loading->{ }
                is ApiResult.Error->{
                    UIExtensions.showSnackBar(this@ArtistAlbumsFragment.lv_artist_album,this@ArtistAlbumsFragment.getString(R.string.unexpected_error))
                    Timber.d("result : error isSplash : false")
                }
                is ApiResult.Success->{
                    Timber.d("result : succes isSplash : false")
                }
            }
        })

        viewModel.isNetworkError.observe(viewLifecycleOwner,{
            if(it){
                UIExtensions.showSnackBar(this@ArtistAlbumsFragment.lv_artist_album,this@ArtistAlbumsFragment.getString(R.string.network_error))
            }
        })
    }
}