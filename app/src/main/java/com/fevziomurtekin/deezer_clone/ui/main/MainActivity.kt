package com.fevziomurtekin.deezer_clone.ui.main
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Observer
import com.fevziomurtekin.deezer_clone.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import com.fevziomurtekin.deezer_clone.core.Result

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @VisibleForTesting val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.fetchGenreList()
        viewModel.genreListLiveData.observe(this, Observer {
            Timber.d("result:${it}")
            when(it){
                Result.Loading->{
                    Toast.makeText(this@MainActivity, "Loading", Toast.LENGTH_SHORT).show()
                }
                Result.Error->{
                    Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                }
                is Result.Succes->{
                    textview.text = it.data.toString()
                }
            }
        })

    }
}