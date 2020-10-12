package com.fevziomurtekin.deezer.data.genre

import kotlinx.serialization.Serializable

@Serializable
data class GenreResponse(
    val `data`: List<Data>
)