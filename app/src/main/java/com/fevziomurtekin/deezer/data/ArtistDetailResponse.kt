package com.fevziomurtekin.deezer.data

import com.google.gson.annotations.SerializedName

data class ArtistDetailResponse(
    @SerializedName("id") val id: String,
    @SerializedName("link") val link: String,
    @SerializedName("name") val name: String,
    @SerializedName("nb_album") val nb_album: Int,
    @SerializedName("nb_fan") val nb_fan: Int,
    @SerializedName("picture") val picture: String,
    @SerializedName("picture_big") val picture_big: String,
    @SerializedName("picture_medium") val picture_medium: String,
    @SerializedName("picture_small") val picture_small: String,
    @SerializedName("picture_xl") val picture_xl: String,
    @SerializedName("radio") val radio: Boolean,
    @SerializedName("share") val share: String,
    @SerializedName("tracklist") val tracklist: String,
    @SerializedName("type") val type: String
)