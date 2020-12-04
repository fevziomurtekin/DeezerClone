package com.fevziomurtekin.deezer.data

import com.google.gson.annotations.SerializedName

data class ArtistsResponse(
    @SerializedName("data")
    val artistData: List<ArtistData>
)

data class ArtistData(
    val id: String,
    val name: String,
    val picture: String,
    val picture_big: String,
    val picture_medium: String,
    val picture_small: String,
    val picture_xl: String,
    val radio: Boolean,
    val tracklist: String,
    val type: String
)
