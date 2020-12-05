package com.fevziomurtekin.deezer.ui.genre

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.fevziomurtekin.deezer.R
import com.fevziomurtekin.deezer.core.Env
import com.fevziomurtekin.deezer.data.Data
import com.fevziomurtekin.deezer.databinding.ItemGenreBinding

class GenreAdapter:RecyclerView.Adapter<GenreAdapter.GenreViewHolder>(){

    private val items:MutableList<Data> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding=DataBindingUtil
            .inflate<ItemGenreBinding>(inflater,R.layout.item_genre,parent,false)

        return GenreViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position = adapterPosition.takeIf { p-> p != NO_POSITION }
                    ?: return@setOnClickListener

                it.findNavController().navigate(
                        R.id.action_genre_list,
                        bundleOf(Env.BUND_ID to items[position].id
                            ,Env.BUND_NAME to items[position].name
                        ))
            }
        }
    }

    fun addGenreList(genreList:List<Data>){
        val previousSize = items.size
        items.addAll(genreList)
        notifyItemRangeChanged(previousSize,items.size)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.binding.apply {
            genre = items[position]
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int = items.size

    class GenreViewHolder(val binding:ItemGenreBinding):RecyclerView.ViewHolder(binding.root)
}