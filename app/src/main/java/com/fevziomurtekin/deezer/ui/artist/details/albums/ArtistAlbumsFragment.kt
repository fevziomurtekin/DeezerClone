package com.fevziomurtekin.deezer.ui.artist.details.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.fevziomurtekin.deezer.R
import com.fevziomurtekin.deezer.core.Env
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.core.extensions.UIExtensions
import com.fevziomurtekin.deezer.core.ui.DataBindingFragment
import com.fevziomurtekin.deezer.data.ArtistAlbumData
import com.fevziomurtekin.deezer.databinding.FragmentArtistAlbumsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_artist_albums.*

@AndroidEntryPoint
class ArtistAlbumsFragment(private val artistID:String): DataBindingFragment() {

    @VisibleForTesting val viewModel: ArtistAlbumViewModel by viewModels()
    private lateinit var binding: FragmentArtistAlbumsBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = binding(inflater,R.layout.fragment_artist_albums,container)
        return binding.root
    }

    override fun getSafeArgs() = Unit

    override fun initBinding() {
        binding.apply {
            lifecycleOwner = this@ArtistAlbumsFragment
            adapter = ArtistAlbumAdapter(::onClickAlbumItem)
            vm = viewModel
        }
    }

    override fun setListeners() = Unit

    override fun observeLiveData() {
        viewModel.fetchArtistAlbum(artistID)
        viewModel.result.observe(viewLifecycleOwner, {
            when(it){
                ApiResult.Loading->{ }
                is ApiResult.Error->{
                    UIExtensions.showSnackBar(
                        this@ArtistAlbumsFragment.lv_artist_album,
                        this@ArtistAlbumsFragment.getString(R.string.unexpected_error))
                }
                is ApiResult.Success-> Unit
            }
        })

        viewModel.isNetworkError.observe(viewLifecycleOwner,{
            if(it){
                UIExtensions.showSnackBar(
                    this@ArtistAlbumsFragment.lv_artist_album,
                    this@ArtistAlbumsFragment.getString(R.string.network_error))
            }
        })
    }

    private fun onClickAlbumItem(data: ArtistAlbumData) {
        findNavController().navigate(
            R.id.action_album_details,
            bundleOf(
                Env.BUND_ID to data.id,
                Env.BUND_NAME to data.title
            )
        )
    }

}
