package com.fevziomurtekin.deezer.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.fevziomurtekin.deezer.R
import com.fevziomurtekin.deezer.data.SearchData
import com.fevziomurtekin.deezer.databinding.ItemSearchAlbumBinding

class SearchAlbumAdapter(
    val onClickItem: (SearchData) -> Unit
) : RecyclerView.Adapter<SearchAlbumAdapter.SearchAlbumViewHolder>() {

    private val items: MutableList<SearchData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAlbumViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil
                .inflate<ItemSearchAlbumBinding>(inflater, R.layout.item_search_album, parent, false)

        return SearchAlbumViewHolder(binding).apply {

            binding.root.setOnClickListener {it->
                val position = adapterPosition.takeIf { p -> p != RecyclerView.NO_POSITION } ?: 0
                onClickItem.invoke(items[position])
            }
        }
    }

    fun addAlbumSearch(searchList: List<SearchData>) {
        val previousSize = items.size
        items.addAll(searchList)
        notifyItemRangeChanged(previousSize, items.size)
    }

    override fun onBindViewHolder(holder: SearchAlbumViewHolder, position: Int) {
        holder.binding.apply {
            search = items[position]
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int = items.size

    class SearchAlbumViewHolder(val binding: ItemSearchAlbumBinding) :
            RecyclerView.ViewHolder(binding.root)
}
