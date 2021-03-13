package com.fevziomurtekin.deezer.ui.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.fevziomurtekin.deezer.R
import com.fevziomurtekin.deezer.databinding.ItemFavoritesBinding
import com.fevziomurtekin.deezer.entities.AlbumEntity
import timber.log.Timber


class FavoritesAdapter(val listener:OnClick): RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    interface OnClick{
        fun onItemClickListener(v: View, trackPos:Int, item: List<AlbumEntity>)
    }

    private val items: MutableList<AlbumEntity> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil
            .inflate<ItemFavoritesBinding>(inflater, R.layout.item_favorites, parent, false)

        return FavoritesViewHolder(binding).apply {

            binding.cardView.setOnClickListener {
                val position = adapterPosition
                listener.onItemClickListener(it, position, items)
            }


        }
    }

    fun addFavoritesList(favorites: List<AlbumEntity>) {
        Timber.d("Favorites addFavorites $favorites")
        val previousSize = items.size
        items.addAll(favorites)
        notifyItemRangeChanged(previousSize, items.size)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
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
