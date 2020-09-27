package com.fevziomurtekin.deezer_clone.ui.artistdetails.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.fevziomurtekin.deezer_clone.R
import com.fevziomurtekin.deezer_clone.core.DataBindingFragment
import com.fevziomurtekin.deezer_clone.core.Result
import com.fevziomurtekin.deezer_clone.core.UIExtensions
import com.fevziomurtekin.deezer_clone.databinding.FragmentArtistAlbumsBinding
import com.fevziomurtekin.deezer_clone.ui.artistdetails.related.ArtistRelatedAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_artist_albums.*
import kotlinx.android.synthetic.main.fragment_artists.*
import kotlinx.android.synthetic.main.fragment_artists.lv_main
import timber.log.Timber

@AndroidEntryPoint
class ArtistAlbumsFragment(private val artistID:String):DataBindingFragment() {

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
                Result.Loading->{ }
                Result.Error->{
                    UIExtensions.showSnackBar(this@ArtistAlbumsFragment.lv_artist_album,this@ArtistAlbumsFragment.getString(R.string.unexpected_error))
                    Timber.d("result : error isSplash : false")
                }
                is Result.Succes->{
                    Timber.d("result : succes isSplash : false")
                }
            }
        })
    }
}