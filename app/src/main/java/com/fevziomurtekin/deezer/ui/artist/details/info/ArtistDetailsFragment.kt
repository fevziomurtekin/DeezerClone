package com.fevziomurtekin.deezer.ui.artist.details.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.viewModels
import com.fevziomurtekin.deezer.R
import com.fevziomurtekin.deezer.core.Env
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.core.extensions.UIExtensions
import com.fevziomurtekin.deezer.core.ui.DataBindingFragment
import com.fevziomurtekin.deezer.databinding.FragmentArtistDetailsBinding
import com.fevziomurtekin.deezer.ui.artist.details.albums.ArtistAlbumsFragment
import com.fevziomurtekin.deezer.ui.artist.details.related.ArtistRelatedFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_artist_details.*
import timber.log.Timber

@AndroidEntryPoint
class ArtistDetailsFragment : DataBindingFragment() {

    private lateinit var binding: FragmentArtistDetailsBinding
    @VisibleForTesting
    val viewModel: ArtistDetailsViewModel by viewModels()
    var id:String = "0" //default value.
    private val tabList:MutableList<String> = mutableListOf("albums", "related details")


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = binding(inflater,R.layout.fragment_artist_details,container)
        return binding.root
    }


    override fun initBinding() {
        binding.apply {
            lifecycleOwner = this@ArtistDetailsFragment
            vpadapter = ADCategories(activity?.supportFragmentManager!!,tabList,id)
            vp = viewPager
            vm = viewModel
        }
    }

    override fun getSafeArgs(){
        arguments?.let {
            id = it.getString(Env.BUND_ID).let { s->
                if(s.isNullOrEmpty()) "0" else s
            }
        }
    }

    override fun setListeners() { }

    override fun observeLiveData() {
        viewModel.fetchArtistDetails(id)
        viewModel.result.observe(viewLifecycleOwner, {
            when(it){
                ApiResult.Loading->{ }
                is ApiResult.Error->{
                    UIExtensions.showSnackBar(this@ArtistDetailsFragment.cl_artist_details,this@ArtistDetailsFragment.getString(R.string.unexpected_error))
                    Timber.d("result : error isSplash : false")
                }
                is ApiResult.Success->{
                    Timber.d("result : succes isSplash : false")
                }
            }
        })

        viewModel.isNetworkError.observe(viewLifecycleOwner,{
            if(it){
                UIExtensions.showSnackBar(this@ArtistDetailsFragment.cl_artist_details,this@ArtistDetailsFragment.getString(R.string.network_error))
            }
        })
    }
}

class ADCategories(fragmentManager: FragmentManager,
    val items:MutableList<String>,val id:String
):FragmentPagerAdapter(fragmentManager,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    override fun getItem(position: Int): Fragment = when(position){
        0-> ArtistAlbumsFragment(id)
        else -> ArtistRelatedFragment(id)
    }

    override fun getCount(): Int = items.size

    override fun getPageTitle(position: Int): CharSequence? = items[position]

    override fun getItemId(position: Int): Long =  View.generateViewId().toLong()


}