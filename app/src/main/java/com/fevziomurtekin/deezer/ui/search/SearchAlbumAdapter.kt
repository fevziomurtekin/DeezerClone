package com.fevziomurtekin.deezer.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.fevziomurtekin.deezer.R
import com.fevziomurtekin.deezer.core.Env
import com.fevziomurtekin.deezer.data.search.SearchData
import com.fevziomurtekin.deezer.databinding.ItemSearchAlbumBinding
import timber.log.Timber


class SearchAlbumAdapter: RecyclerView.Adapter<SearchAlbumAdapter.SearchAlbumViewHolder>() {

    private val items: MutableList<SearchData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAlbumViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil
                .inflate<ItemSearchAlbumBinding>(inflater, R.layout.item_search_album, parent, false)

        return SearchAlbumViewHolder(binding).apply {

            binding.root.setOnClickListener {it->
                val position = adapterPosition.takeIf { p -> p != RecyclerView.NO_POSITION } ?: 0

                it.findNavController().navigate(
                        R.id.action_album_details,
                        bundleOf(
                                Env.BUND_ID to items[position].id,
                                Env.BUND_NAME to items[position].title
                        ))
            }
        }
    }

    fun addAlbumSearch(searchList: List<SearchData>) {
        val previousSize = items.size
        items.addAll(searchList)
        // Timber.d("GenreAdapter  size : $previousSize  \t genreList size : ${genreList.size} item size : ${items.size} ")
        notifyItemRangeChanged(previousSize, items.size)
    }

    override fun onBindViewHolder(holder: SearchAlbumViewHolder, position: Int) {
        //Timber.d("Items$position ${items[position].toString()}")
        holder.binding.apply {
            Timber.d("binding..")
            search = items[position]
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int = items.size

    class SearchAlbumViewHolder(val binding: ItemSearchAlbumBinding) :
            RecyclerView.ViewHolder(binding.root)
}