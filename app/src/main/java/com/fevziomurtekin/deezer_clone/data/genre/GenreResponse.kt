package com.fevziomurtekin.deezer_clone.data.genre

import kotlinx.serialization.Serializable

@Serializable
data class GenreResponse(
    val `data`: List<Data>
)