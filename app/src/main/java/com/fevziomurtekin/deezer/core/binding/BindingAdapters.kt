package com.fevziomurtekin.deezer.core.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView


/**
 * Help in the development of this class, the application named 'Pokedex' @Skydoves user has helped.
 * https://github.com/skydoves/Pokedex/blob/master/app/src/main/java/com/skydoves/pokedex/binding/RecyclerViewBinding.kt
 * */

@BindingAdapter("adapter")
fun bindAdapter(view:RecyclerView, adapter:RecyclerView.Adapter<*>){
    view.adapter = adapter
}

