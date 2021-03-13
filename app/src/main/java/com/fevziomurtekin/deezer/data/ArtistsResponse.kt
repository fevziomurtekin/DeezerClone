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
    @SerializedName("picture_big") val pictureBig: String,
    @SerializedName("picture_medium") val pictureMedium: String,
    @SerializedName("picture_small") val pictureSmall: String,
    @SerializedName("picture_xl") val pictureXl: String,
    @SerializedName("radio") val radio: Boolean,
    @SerializedName("tracklist") val tracklist: String,
    @SerializedName("type") val type: String
)
