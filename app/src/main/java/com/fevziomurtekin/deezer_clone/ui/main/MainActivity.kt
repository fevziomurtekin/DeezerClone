package com.fevziomurtekin.deezer_clone.ui.main
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.fevziomurtekin.deezer_clone.R
import com.fevziomurtekin.deezer_clone.core.DataBindingActivity
import com.fevziomurtekin.deezer_clone.core.Env
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import com.fevziomurtekin.deezer_clone.core.Result
import com.fevziomurtekin.deezer_clone.core.UIExtensions
import com.fevziomurtekin.deezer_clone.data.mediaplayer.MediaPlayerState
import com.fevziomurtekin.deezer_clone.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import java.time.Duration

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
                Result.Loading->{
                    viewModel.isSplash.postValue(true)
                }
                Result.Error->{
                    viewModel.isSplash.postValue(false)
                    UIExtensions.showSnackBar(this@MainActivity.constraint_main,this@MainActivity.getString(R.string.unexpected_error))
                    Timber.d("result : error isSplash : false")
                }
                is Result.Succes->{
                    viewModel.isSplash.postValue(false)
                    Timber.d("result : succes isSplash : false")
                }
            }
        })

        /* Navigation destination listener. */
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
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
            viewModel.isGoneMediaPlayer.set(false)
        }
        else{
            super.onBackPressed()
        }
    }
}