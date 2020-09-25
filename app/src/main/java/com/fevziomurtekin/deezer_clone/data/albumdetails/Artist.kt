package com.fevziomurtekin.deezer_clone.data.albumdetails

import kotlinx.serialization.Serializable

@Serializable
data class Artist(
    val id: String,
    val name: String,
    val picture: String,
    val picture_big: String,
    val picture_medium: String,
    val picture_small: String,
    val picture_xl: String,
    val tracklist: String,
    val type: String
)