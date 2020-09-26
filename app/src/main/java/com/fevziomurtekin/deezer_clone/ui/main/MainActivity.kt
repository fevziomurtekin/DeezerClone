package com.fevziomurtekin.deezer_clone.ui.main
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.fevziomurtekin.deezer_clone.R
import com.fevziomurtekin.deezer_clone.core.DataBindingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import com.fevziomurtekin.deezer_clone.core.Result
import com.fevziomurtekin.deezer_clone.core.UIExtensions
import com.fevziomurtekin.deezer_clone.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.time.Duration

@AndroidEntryPoint
class MainActivity : DataBindingActivity() {

    @VisibleForTesting val viewModel : MainViewModel by viewModels()
    private val binding: ActivityMainBinding by binding(R.layout.activity_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            lifecycleOwner = this@MainActivity
            vm = viewModel
        }

        viewModel.fetchGenreList()
        viewModel.genreListLiveData.observe(this, Observer {
            Timber.d("result:${it}")
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

        /*Navigation.findNavController(
                this,R.id.nav_host_fragment
        ).addOnDestinationChangedListener{ controller, destination, arguments->
            when(destination.id){
                R.id.genre->{ materialToolbar.title = getString(R.string.app_name) }
                R.id.search->{ materialToolbar.title = getString(R.string.search) }
                R.id.favorites->{ materialToolbar.title = getString(R.string.favorites) }

            }
        }*/
    }
}