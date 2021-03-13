package com.fevziomurtekin.deezer.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.fevziomurtekin.deezer.R
import com.fevziomurtekin.deezer.databinding.ItemSearchBinding
import com.fevziomurtekin.deezer.entities.SearchEntity
import timber.log.Timber

class RecentSearchAdapter(var listener:RecentSearchListener):
    RecyclerView.Adapter<RecentSearchAdapter.RecentSearchViewHolder>(){

    interface RecentSearchListener{
        fun recentSearchListener(query: String)
    }

    private val items: MutableList<SearchEntity> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentSearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil
                .inflate<ItemSearchBinding>(inflater, R.layout.item_search, parent, false)

        return RecentSearchViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position = adapterPosition
                listener.recentSearchListener(items[position].q.toString())
                Toast.makeText(binding.root.context,"Searching ${items[position].q}", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun addRecentSearch(searchList: List<SearchEntity>) {
        val previousSize = items.size
        items.addAll(searchList)
        notifyItemRangeChanged(previousSize, items.size)
    }

    override fun onBindViewHolder(holder: RecentSearchViewHolder, position: Int) {
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
