package com.fevziomurtekin.deezer.ui.artist.details.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.fevziomurtekin.deezer.R
import com.fevziomurtekin.deezer.data.ArtistAlbumData
import com.fevziomurtekin.deezer.databinding.ItemArtistAlbumBinding
import timber.log.Timber


class ArtistAlbumAdapter(
    val onClickItem: (ArtistAlbumData) -> Unit
) : RecyclerView.Adapter<ArtistAlbumAdapter.ArtistAlbumViewHolder>() {

    private val items: MutableList<ArtistAlbumData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistAlbumViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil
            .inflate<ItemArtistAlbumBinding>(inflater, R.layout.item_artist_album, parent, false)

        return ArtistAlbumViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position = adapterPosition.takeIf { p -> p != RecyclerView.NO_POSITION }
                    ?: return@setOnClickListener
                onClickItem.invoke(items[position])
            }
        }
    }

    fun addAlbumList(artistList: List<ArtistAlbumData>) {
        val previousSize = items.size
        items.addAll(artistList)
        notifyItemRangeChanged(previousSize, items.size)
    }

    override fun onBindViewHolder(holder: ArtistAlbumViewHolder, position: Int) {
        holder.binding.apply {
            Timber.d("binding..")
            album = items[position]
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int = items.size

    class ArtistAlbumViewHolder(val binding: ItemArtistAlbumBinding) :
        RecyclerView.ViewHolder(binding.root)
}
