package com.fevziomurtekin.deezer.ui.artist.details.related

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
import com.fevziomurtekin.deezer.data.ArtistRelatedData
import com.fevziomurtekin.deezer.databinding.FragmentArtistRelatedBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_artist_related.*

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

    override fun getSafeArgs() = Unit

    override fun initBinding() {
        binding.apply {
            lifecycleOwner = this@ArtistRelatedFragment
            adapter = ArtistRelatedAdapter(::onClickArtistItem)
            vm = viewModel
        }
    }

    override fun setListeners() = Unit

    override fun observeLiveData() {
        viewModel.fetchArtistRelated(artistID)
        viewModel.result.observe(viewLifecycleOwner, {
            when(it){
                ApiResult.Loading->{ }
                is ApiResult.Error->{
                    UIExtensions.showSnackBar(
                        this@ArtistRelatedFragment.lv_artist_related,
                        this@ArtistRelatedFragment.getString(R.string.unexpected_error))
                }
                is ApiResult.Success-> Unit
            }
        })
    }

    private fun onClickArtistItem(data: ArtistRelatedData) {
        findNavController().navigate(
            R.id.action_artist_details,
            bundleOf(
                Env.BUND_ID to data.id,
                Env.BUND_NAME to data.name
            )
        )
    }
}
