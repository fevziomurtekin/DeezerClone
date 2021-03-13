package com.fevziomurtekin.deezer.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.viewModels
import com.fevziomurtekin.deezer.R
import com.fevziomurtekin.deezer.core.ui.DataBindingFragment
import com.fevziomurtekin.deezer.databinding.FragmentFavoritesBinding
import com.fevziomurtekin.deezer.entities.AlbumEntity
import com.fevziomurtekin.deezer.ui.main.MainActivity
import com.fevziomurtekin.deezer.ui.main.playMediaPlayer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment: DataBindingFragment() {

    lateinit var binding:FragmentFavoritesBinding
    @VisibleForTesting
    val viewModel:FavoritesViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = binding(inflater,R.layout.fragment_favorites,container)
        return binding.root
    }

    override fun getSafeArgs() = Unit

    override fun initBinding() {
        binding.apply {
            lifecycleOwner = this@FavoritesFragment
            vm = viewModel
            adapter = FavoritesAdapter(object : FavoritesAdapter.OnClick {
                override fun onItemClickListener(v: View,trackPos:Int, albumList:List<AlbumEntity>) {
                    ((this@FavoritesFragment).requireActivity() as MainActivity)
                        .viewModel.apply {
                            positionTrack = trackPos
                            isGoneMediaPlayer.set(true)
                            playMediaPlayer()
                        }
                }
            })
        }
    }

    override fun setListeners() = Unit

    override fun observeLiveData() {
        viewModel.fetchFavorites()
    }
}
