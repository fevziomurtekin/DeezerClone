package com.fevziomurtekin.deezer.ui.artist.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.fevziomurtekin.deezer.R
import com.fevziomurtekin.deezer.data.ArtistData
import com.fevziomurtekin.deezer.databinding.ItemArtistBinding
import timber.log.Timber


class ArtistAdapter(
    val onClickItem: (ArtistData) -> Unit
) : RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>(){

    private val items:MutableList<ArtistData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding= DataBindingUtil
            .inflate<ItemArtistBinding>(inflater, R.layout.item_artist,parent,false)

        return ArtistViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position = adapterPosition.takeIf { p-> p != RecyclerView.NO_POSITION }
                    ?: return@setOnClickListener
                onClickItem(items[position])
            }
        }
    }

    fun addArtistList(artistList:List<ArtistData>){
        val previousSize = items.size
        items.addAll(artistList)
        notifyItemRangeChanged(previousSize,items.size)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.binding.apply {
            Timber.d("binding..")
            artist = items[position]
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int = items.size

    class ArtistViewHolder(val binding: ItemArtistBinding): RecyclerView.ViewHolder(binding.root)
}
