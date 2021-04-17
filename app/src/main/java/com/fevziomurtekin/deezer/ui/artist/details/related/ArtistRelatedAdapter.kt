package com.fevziomurtekin.deezer.ui.artist.details.related

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.fevziomurtekin.deezer.R
import com.fevziomurtekin.deezer.data.ArtistRelatedData
import com.fevziomurtekin.deezer.databinding.ItemArtistRelatedBinding
import timber.log.Timber


class ArtistRelatedAdapter(
    val onClickItem: (ArtistRelatedData) -> Unit
) : RecyclerView.Adapter<ArtistRelatedAdapter.ArtistRelatedViewHolder>() {

    private val items: MutableList<ArtistRelatedData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistRelatedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil
            .inflate<ItemArtistRelatedBinding>(inflater, R.layout.item_artist_related, parent, false)

        return ArtistRelatedViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position = adapterPosition.takeIf { p -> p != RecyclerView.NO_POSITION }
                    ?: return@setOnClickListener
                onClickItem.invoke(items[position])
            }
        }
    }

    fun addRelatedList(artistList: List<ArtistRelatedData>) {
        val previousSize = items.size
        items.addAll(artistList)
        notifyItemRangeChanged(previousSize, items.size)
    }

    override fun onBindViewHolder(holder: ArtistRelatedViewHolder, position: Int) {
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
