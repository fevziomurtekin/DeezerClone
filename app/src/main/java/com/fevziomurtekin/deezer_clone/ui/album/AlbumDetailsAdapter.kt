package com.fevziomurtekin.deezer_clone.ui.album

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.fevziomurtekin.deezer_clone.R
import com.fevziomurtekin.deezer_clone.core.Env
import com.fevziomurtekin.deezer_clone.data.albumdetails.AlbumData
import com.fevziomurtekin.deezer_clone.data.artistdetails.ArtistAlbumData
import com.fevziomurtekin.deezer_clone.databinding.ItemAlbumBinding
import com.fevziomurtekin.deezer_clone.databinding.ItemArtistAlbumBinding
import kotlinx.android.synthetic.main.item_album.view.*
import timber.log.Timber


class AlbumDetailsAdapter: RecyclerView.Adapter<AlbumDetailsAdapter.AlbumDetailsViewHolder>() {

    private val items: MutableList<AlbumData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumDetailsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil
            .inflate<ItemAlbumBinding>(inflater, R.layout.item_album, parent, false)

        return AlbumDetailsViewHolder(binding).apply {
            val position = adapterPosition.takeIf { p -> p != RecyclerView.NO_POSITION }
            binding.root.ibn_fav.setOnClickListener {
                Toast.makeText(binding.root.context,"Add to Favorites!", Toast.LENGTH_LONG).show()}
            binding.root.ibn_share.setOnClickListener {
                Toast.makeText(binding.root.context,"Sharing Tracks... Waiting.", Toast.LENGTH_LONG).show()}
        }
    }

    fun addAlbumTracks(artistList: List<AlbumData>) {
        val previousSize = items.size
        items.addAll(artistList)
        // Timber.d("GenreAdapter  size : $previousSize  \t genreList size : ${genreList.size} item size : ${items.size} ")
        notifyItemRangeChanged(previousSize, items.size)
    }

    override fun onBindViewHolder(holder: AlbumDetailsViewHolder, position: Int) {
        //Timber.d("Items$position ${items[position].toString()}")
        holder.binding.apply {
            Timber.d("binding..")
            album = items[position]
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int = items.size

    class AlbumDetailsViewHolder(val binding: ItemAlbumBinding) :
        RecyclerView.ViewHolder(binding.root)
}