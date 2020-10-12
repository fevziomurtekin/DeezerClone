package com.fevziomurtekin.deezer.ui.artistdetails.related

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.fevziomurtekin.deezer.R
import com.fevziomurtekin.deezer.core.Env
import com.fevziomurtekin.deezer.data.artistdetails.ArtistRelatedData
import com.fevziomurtekin.deezer.databinding.ItemArtistRelatedBinding
import timber.log.Timber


class ArtistRelatedAdapter: RecyclerView.Adapter<ArtistRelatedAdapter.ArtistRelatedViewHolder>() {

    private val items: MutableList<ArtistRelatedData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistRelatedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil
            .inflate<ItemArtistRelatedBinding>(inflater, R.layout.item_artist_related, parent, false)

        return ArtistRelatedViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position = adapterPosition.takeIf { p -> p != RecyclerView.NO_POSITION }
                    ?: return@setOnClickListener

                it.findNavController().navigate(
                    R.id.action_artist_details,
                    bundleOf(
                        Env.BUND_ID to items[position].id,
                        Env.BUND_NAME to items[position].name
                    )
                )
            }
        }
    }

    fun addRelatedList(artistList: List<ArtistRelatedData>) {
        val previousSize = items.size
        items.addAll(artistList)
        // Timber.d("GenreAdapter  size : $previousSize  \t genreList size : ${genreList.size} item size : ${items.size} ")
        notifyItemRangeChanged(previousSize, items.size)
    }

    override fun onBindViewHolder(holder: ArtistRelatedViewHolder, position: Int) {
        //Timber.d("Items$position ${items[position].toString()}")
        holder.binding.apply {
            Timber.d("binding..")
            artist = items[position]
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int = items.size

    class ArtistRelatedViewHolder(val binding: ItemArtistRelatedBinding) :
        RecyclerView.ViewHolder(binding.root)
}