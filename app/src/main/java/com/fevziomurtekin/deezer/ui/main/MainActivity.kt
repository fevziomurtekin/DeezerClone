package com.fevziomurtekin.deezer.ui.main
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.fevziomurtekin.deezer.R
import com.fevziomurtekin.deezer.core.ui.DataBindingActivity
import com.fevziomurtekin.deezer.core.Env
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.core.extensions.UIExtensions
import com.fevziomurtekin.deezer.data.mediaplayer.MediaPlayerState
import com.fevziomurtekin.deezer.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : DataBindingActivity() {

    @VisibleForTesting val viewModel : MainViewModel by viewModels()
    private val binding: ActivityMainBinding by binding(R.layout.activity_main)
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            lifecycleOwner = this@MainActivity
            vm = viewModel
            navController = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
        }
        viewModel.fetchGenreList()

        viewModel.genreListLiveData.observe(this, Observer {
            //Timber.d("result:${it}")
            when(it){
                ApiResult.Loading->{
                    viewModel.isSplash.postValue(true)
                }
                is ApiResult.Error->{
                    viewModel.isSplash.postValue(false)
                    UIExtensions.showSnackBar(this@MainActivity.constraint_main,this@MainActivity.getString(R.string.unexpected_error))
                    Timber.d("result : error isSplash : false")
                }
                is ApiResult.Success->{
                    viewModel.isSplash.postValue(false)
                    Timber.d("result : succes isSplash : false")
                }
            }
        })


        /* Navigation destination listener. */
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            /* if mediaplayer or volumeController visible, hidden views.*/
            if(viewModel.isGoneMediaPlayer.get()){
                viewModel.hideMediaPlayer()
            }

            var name = getString(R.string.app_name)
            arguments?.let {
                name = it[Env.BUND_NAME].toString()
            }

            Timber.d("name : $name")



            when(destination.id){
                        R.id.genre->{ materialToolbar.title = getString(R.string.app_name) }
                        R.id.search->{ materialToolbar.title = getString(R.string.search) }
                        R.id.favorites->{ materialToolbar.title = getString(R.string.favorites) }
                        R.id.genre_list,
                        R.id.artist_details,
                        R.id.album_details,->{ materialToolbar.title = name }
                    }
                }

        /* BottomNavigationBar Click Listener */
        bottom_navigation.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.itemMusic->{ navController.navigate(R.id.action_genre) }
                R.id.itemSearch->{ navController.navigate(R.id.action_search) }
                R.id.itemFavorites->{  navController.navigate(R.id.favorites) }
            }
        }

        binding.ibtnPlayPlayer.setOnClickListener {
            when(viewModel.mediaPlayerState.value) {
                MediaPlayerState.PLAYING->viewModel.playMusic()
                MediaPlayerState.PAUSED->viewModel.resume()
                else->viewModel.stop()
            }
        }

        binding.ibtnTrackForward.setOnClickListener {
            viewModel.forwardTrack()
        }

        binding.ibtnTrackPrevious.setOnClickListener {
            viewModel.previouslyTrack()
        }


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