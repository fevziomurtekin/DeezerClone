package com.fevziomurtekin.deezer.ui.main
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.fevziomurtekin.deezer.R
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.core.extensions.UIExtensions
import com.fevziomurtekin.deezer.core.ui.DataBindingActivity
import com.fevziomurtekin.deezer.data.MediaPlayerState
import com.fevziomurtekin.deezer.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : DataBindingActivity() {

    @VisibleForTesting val viewModel : MainViewModel by viewModels()
    private val binding: ActivityMainBinding by binding(R.layout.activity_main)
    internal lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = this@MainActivity
            vm = viewModel
            navController = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
                    as NavHostFragment).navController
        }
        observeLiveData()
        bottomNavigationListener()
        navControllerListener()
        setListeners()
    }

    private fun observeLiveData() {
        viewModel.fetchGenreList()
        viewModel.genreListLiveData.observe(this, {
            when(it){
                ApiResult.Loading->{
                    viewModel.isSplash.postValue(true)
                }
                is ApiResult.Error->{
                    viewModel.isSplash.postValue(false)
                    UIExtensions.showSnackBar(
                        this@MainActivity.constraint_main,
                        this@MainActivity.getString(R.string.unexpected_error)
                    )
                }
                is ApiResult.Success->{
                    viewModel.isSplash.postValue(false)
                }
            }
        })
    }

    private fun setListeners() {
        binding.ibtnPlayPlayer.setOnClickListener {
            when(viewModel.mediaPlayerState.value) {
                MediaPlayerState.PLAYING->viewModel.playMediaPlayer()
                MediaPlayerState.PAUSED->viewModel.resumeMediaPlayer()
                else->viewModel.stopMediaPlayer()
            }
        }

        binding.ibtnTrackForward.setOnClickListener {
            viewModel.forwardTrack()
        }

        binding.ibtnTrackPrevious.setOnClickListener {
            viewModel.previouslyTrack()
        }
    }

    private fun handleDeepLink() {
        navController.handleDeepLink(intent)
    }

    override fun onBackPressed() {
        if(viewModel.isGoneMediaPlayer.get()){
            viewModel.hideMediaPlayer()
        }
        else{
            super.onBackPressed()
        }
    }
}
