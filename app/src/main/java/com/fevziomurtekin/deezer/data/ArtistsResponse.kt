package com.fevziomurtekin.deezer.data

import com.google.gson.annotations.SerializedName

data class ArtistsResponse(
    @SerializedName("data")
    val artistData: List<ArtistData>
)

data class ArtistData(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("picture") val picture: String,
    @SerializedName("picture_big") val picture_big: String,
    @SerializedName("picture_medium") val picture_medium: String,
    @SerializedName("picture_small") val picture_small: String,
    @SerializedName("picture_xl") val picture_xl: String,
    @SerializedName("radio") val radio: Boolean,
    @SerializedName("tracklist") val tracklist: String,
    @SerializedName("type") val type: String
)
