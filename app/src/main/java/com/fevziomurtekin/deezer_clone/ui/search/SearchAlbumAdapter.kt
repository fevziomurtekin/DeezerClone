package com.fevziomurtekin.deezer_clone.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.fevziomurtekin.deezer_clone.R
import com.fevziomurtekin.deezer_clone.data.search.SearchData
import com.fevziomurtekin.deezer_clone.databinding.ItemSearchAlbumBinding
import timber.log.Timber


class SearchAlbumAdapter: RecyclerView.Adapter<SearchAlbumAdapter.SearchAlbumViewHolder>() {

    private val items: MutableList<SearchData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAlbumViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil
                .inflate<ItemSearchAlbumBinding>(inflater, R.layout.item_search_album, parent, false)

        return SearchAlbumViewHolder(binding).apply {
            val position = adapterPosition.takeIf { p -> p != RecyclerView.NO_POSITION }.let {
                it?:0
            }

           /* binding.root.setOnClickListener {
                Toast.makeText(binding.root.context,"Searching ${items[position].q}", Toast.LENGTH_LONG).show()}*/
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