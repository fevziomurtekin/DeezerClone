package com.fevziomurtekin.deezer.data

import com.google.gson.annotations.SerializedName

data class ArtistAlbumResponse(
    @SerializedName("data") val `data`: List<ArtistAlbumData>,
    @SerializedName("next") val next: String="",
    @SerializedName("total") val total: Int
)

data class ArtistAlbumData(
    @SerializedName("cover") val cover: String="",
    @SerializedName("cover_big") val cover_big: String="",
    @SerializedName("cover_medium") val cover_medium: String="",
    @SerializedName("cover_small") val cover_small: String="",
    @SerializedName("cover_xl") val cover_xl: String="",
    @SerializedName("explicit_lyrics") val explicit_lyrics: Boolean?=false,
    @SerializedName("fans") val fans: Int=0,
    @SerializedName("genre_id") val genre_id: Int=0,
    @SerializedName("id") val id: String="",
    @SerializedName("link") val link: String="",
    @SerializedName("md5_image") val md5_image: String="",
    @SerializedName("record_type") val record_type: String="",
    @SerializedName("release_date") val release_date: String="",
    @SerializedName("title") val title: String="",
    @SerializedName("tracklist") val tracklist: String="",
    @SerializedName("type") val type: String=""
)