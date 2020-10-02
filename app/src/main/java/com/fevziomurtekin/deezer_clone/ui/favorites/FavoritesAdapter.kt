package com.fevziomurtekin.deezer_clone.ui.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.fevziomurtekin.deezer_clone.R
import com.fevziomurtekin.deezer_clone.data.albumdetails.AlbumData
import com.fevziomurtekin.deezer_clone.databinding.ItemFavoritesBinding
import timber.log.Timber


class FavoritesAdapter(val listener:OnClick): RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    interface OnClick{
        fun onItemClickListener(v: View, item: MutableMap<Int,List<AlbumData>>)
    }

    private val items: MutableList<AlbumData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil
            .inflate<ItemFavoritesBinding>(inflater, R.layout.item_favorites, parent, false)

        return FavoritesViewHolder(binding).apply {

            binding.cardView.setOnClickListener {
                val position = adapterPosition
                listener.onItemClickListener(it, mutableMapOf(position to items))
            }


        }
    }

    fun addFavoritesList(favorites: List<AlbumData>) {
        Timber.d("Favorites addFavorites $favorites")
        val previousSize = items.size
        items.addAll(favorites)
        // Timber.d("GenreAdapter  size : $previousSize  \t genreList size : ${genreList.size} item size : ${items.size} ")
        notifyItemRangeChanged(previousSize, items.size)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        //Timber.d("Items$position ${items[position].toString()}")
        holder.binding.apply {
            Timber.d("binding..")
            favorite = items[position]
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int = items.size

    class FavoritesViewHolder(val binding: ItemFavoritesBinding) :
        RecyclerView.ViewHolder(binding.root)
}