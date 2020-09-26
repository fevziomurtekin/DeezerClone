package com.fevziomurtekin.deezer_clone.ui.genre

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import androidx.recyclerview.widget.RecyclerView
import com.fevziomurtekin.deezer_clone.R
import com.fevziomurtekin.deezer_clone.data.genre.Data
import com.fevziomurtekin.deezer_clone.databinding.ItemGenreBinding
import timber.log.Timber

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
                        bundleOf("id" to items[position].id,"name" to items[position].name
                        ))
            }
        }
    }

    fun addGenreList(genreList:List<Data>){
        val previousSize = items.size
        items.addAll(genreList)
       // Timber.d("GenreAdapter  size : $previousSize  \t genreList size : ${genreList.size} item size : ${items.size} ")
        notifyItemRangeChanged(previousSize,items.size)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        //Timber.d("Items$position ${items[position].toString()}")
        holder.binding.apply {
            Timber.d("binding..")
            genre = items[position]
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int = items.size

    class GenreViewHolder(val binding:ItemGenreBinding):RecyclerView.ViewHolder(binding.root)
}