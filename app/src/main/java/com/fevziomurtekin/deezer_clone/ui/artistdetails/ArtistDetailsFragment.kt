package com.fevziomurtekin.deezer_clone.ui.artistdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.viewModels
import androidx.viewpager.widget.ViewPager
import com.fevziomurtekin.deezer_clone.R
import com.fevziomurtekin.deezer_clone.core.DataBindingFragment
import com.fevziomurtekin.deezer_clone.core.Env
import com.fevziomurtekin.deezer_clone.core.Result
import com.fevziomurtekin.deezer_clone.core.UIExtensions
import com.fevziomurtekin.deezer_clone.databinding.FragmentArtistDetailsBinding
import com.fevziomurtekin.deezer_clone.databinding.FragmentArtistsBinding
import com.fevziomurtekin.deezer_clone.ui.artist.ArtistViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_artists.*
import timber.log.Timber

@AndroidEntryPoint
class ArtistDetailsFragment : DataBindingFragment() {

    private lateinit var binding: FragmentArtistDetailsBinding
    @VisibleForTesting
    val viewModel: ArtistDetailsViewModel by viewModels()
    var id:String = "0" //default value.
    val tabList:MutableList<String> = mutableListOf("albums", "related details")


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = binding(inflater,R.layout.fragment_artist_details,container)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            id = it.getString(Env.BUND_ID).let { s->
                if(s.isNullOrEmpty()) "0" else s
            }
        }

        Timber.d("artist list - artistdetails : $id")

        binding.apply {
            lifecycleOwner = this@ArtistDetailsFragment
            vpadapter = ADCategories(activity?.supportFragmentManager!!,tabList)
            vp = viewPager
            vm = viewModel
        }

        viewModel.fetchArtistDetails(id)
        viewModel.result.observe(viewLifecycleOwner, {
            when(it){
                //TODO  progress dialog add.
                Result.Loading->{ }
                Result.Error->{
                    UIExtensions.showSnackBar(this@ArtistDetailsFragment.lv_main,this@ArtistDetailsFragment.getString(R.string.unexpected_error))
                    Timber.d("result : error isSplash : false")
                }
                is Result.Succes->{
                    Timber.d("result : succes isSplash : false")
                }
            }
        })

    }
}

class ADCategories(fragmentManager: FragmentManager,
    val items:MutableList<String>
):FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    override fun getItem(position: Int): Fragment = when(position){
        0-> ArtistAlbumsFragment()
        else -> ArtistRelatedFragment()
    }

    override fun getCount(): Int = items.size

    override fun getPageTitle(position: Int): CharSequence? = items[position]

    override fun getItemId(position: Int): Long = View.generateViewId().toLong()

}