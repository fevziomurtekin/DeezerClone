package com.fevziomurtekin.deezer_clone.data.albumdetails

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Artist(
    val id: String,
    val name: String,
    val tracklist: String,
    val type: String
)