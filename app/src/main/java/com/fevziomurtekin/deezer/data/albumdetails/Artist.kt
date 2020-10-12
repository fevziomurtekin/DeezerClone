package com.fevziomurtekin.deezer.data.albumdetails

import kotlinx.serialization.Serializable

@Serializable
data class Artist(
    val id: String,
    val name: String,
    val tracklist: String,
    val type: String
)