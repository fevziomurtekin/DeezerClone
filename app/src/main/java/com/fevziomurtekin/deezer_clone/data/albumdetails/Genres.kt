package com.fevziomurtekin.deezer_clone.data.albumdetails

import com.fevziomurtekin.deezer_clone.data.genre.Data
import kotlinx.serialization.Serializable

@Serializable
data class Genres(
    val `data`: List<Data>
)