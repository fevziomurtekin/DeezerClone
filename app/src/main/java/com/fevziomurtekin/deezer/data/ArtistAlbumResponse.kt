package com.fevziomurtekin.deezer.data

import com.google.gson.annotations.SerializedName

data class ArtistAlbumResponse(
    @SerializedName("data") val data: List<ArtistAlbumData>,
    @SerializedName("next") val next: String="",
    @SerializedName("total") val total: Int
)

data class ArtistAlbumData(
    @SerializedName("cover") val cover: String="",
    @SerializedName("cover_big") val coverBig: String="",
    @SerializedName("cover_medium") val coverMedium: String="",
    @SerializedName("cover_small") val coverSmall: String="",
    @SerializedName("cover_xl") val coverXl: String="",
    @SerializedName("explicit_lyrics") val explicitLyrics: Boolean?=false,
    @SerializedName("fans") val fans: Int=0,
    @SerializedName("genre_id") val genreId: Int=0,
    @SerializedName("id") val id: String="",
    @SerializedName("link") val link: String="",
    @SerializedName("md5_image") val md5Image: String="",
    @SerializedName("record_type") val recordType: String="",
    @SerializedName("release_date") val releaseDate: String="",
    @SerializedName("title") val title: String="",
    @SerializedName("tracklist") val tracklist: String="",
    @SerializedName("type") val type: String=""
)
