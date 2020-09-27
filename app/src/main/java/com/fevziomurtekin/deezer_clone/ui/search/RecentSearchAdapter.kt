package com.fevziomurtekin.deezer_clone.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.fevziomurtekin.deezer_clone.R
import com.fevziomurtekin.deezer_clone.data.search.SearchQuery
import com.fevziomurtekin.deezer_clone.databinding.ItemSearchBinding
import timber.log.Timber


class RecentSearchAdapter: RecyclerView.Adapter<RecentSearchAdapter.RecentSearchViewHolder>() {

    private val items: MutableList<SearchQuery> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentSearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil
                .inflate<ItemSearchBinding>(inflater, R.layout.item_search, parent, false)

        return RecentSearchViewHolder(binding).apply {
            val position = adapterPosition.takeIf { p -> p != RecyclerView.NO_POSITION }.let {
                it?:0
            }

            binding.root.setOnClickListener {
                Toast.makeText(binding.root.context,"Searching ${items[position].q}", Toast.LENGTH_LONG).show()}
        }
    }

    fun addRecentSearch(searchList: List<SearchQuery>) {
        Timber.d("searchList : ${searchList.toString()}")
        val previousSize = items.size
        items.addAll(searchList)
        // Timber.d("GenreAdapter  size : $previousSize  \t genreList size : ${genreList.size} item size : ${items.size} ")
        notifyItemRangeChanged(previousSize, items.size)
    }

    override fun onBindViewHolder(holder: RecentSearchViewHolder, position: Int) {
        //Timber.d("Items$position ${items[position].toString()}")
        holder.binding.apply {
            Timber.d("binding..")
            search = items[position]
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int = items.size

    class RecentSearchViewHolder(val binding: ItemSearchBinding) :
            RecyclerView.ViewHolder(binding.root)
}