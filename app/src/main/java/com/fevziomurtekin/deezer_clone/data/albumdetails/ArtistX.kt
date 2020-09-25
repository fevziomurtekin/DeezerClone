package com.fevziomurtekin.deezer_clone.data.albumdetails

import kotlinx.serialization.Serializable

@Serializable
data class ArtistX(
    val id: String,
    val name: String,
    val tracklist: String,
    val type: String
)