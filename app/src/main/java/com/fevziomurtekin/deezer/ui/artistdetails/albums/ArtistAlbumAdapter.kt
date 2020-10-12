package com.fevziomurtekin.deezer.ui.artistdetails.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.fevziomurtekin.deezer.R
import com.fevziomurtekin.deezer.core.Env
import com.fevziomurtekin.deezer.data.artistdetails.ArtistAlbumData
import com.fevziomurtekin.deezer.databinding.ItemArtistAlbumBinding
import com.fevziomurtekin.deezer.databinding.ItemArtistRelatedBinding
import timber.log.Timber


class ArtistAlbumAdapter: RecyclerView.Adapter<ArtistAlbumAdapter.ArtistAlbumViewHolder>() {

    private val items: MutableList<ArtistAlbumData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistAlbumViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil
            .inflate<ItemArtistAlbumBinding>(inflater, R.layout.item_artist_album, parent, false)

        return ArtistAlbumViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position = adapterPosition.takeIf { p -> p != RecyclerView.NO_POSITION }
                    ?: return@setOnClickListener

                it.findNavController().navigate(
                    R.id.action_album_details,
                    bundleOf(
                        Env.BUND_ID to items[position].id,
                        Env.BUND_NAME to items[position].title
                    )
                )
            }
        }
    }

    fun addAlbumList(artistList: List<ArtistAlbumData>) {
        val previousSize = items.size
        items.addAll(artistList)
        // Timber.d("GenreAdapter  size : $previousSize  \t genreList size : ${genreList.size} item size : ${items.size} ")
        notifyItemRangeChanged(previousSize, items.size)
    }

    override fun onBindViewHolder(holder: ArtistAlbumViewHolder, position: Int) {
        //Timber.d("Items$position ${items[position].toString()}")
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