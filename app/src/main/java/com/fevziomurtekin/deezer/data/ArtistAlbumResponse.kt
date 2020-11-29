package com.fevziomurtekin.deezer.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class ArtistAlbumResponse(
    @SerializedName("data")
    val `data`: List<ArtistAlbumData>,
    val next: String="",
    val total: Int
)

data class ArtistAlbumData(
    val cover: String="",
    val cover_big: String="",
    val cover_medium: String="",
    val cover_small: String="",
    val cover_xl: String="",
    val explicit_lyrics: Boolean?=false,
    val fans: Int=0,
    val genre_id: Int=0,
    val id: String="",
    val link: String="",
    val md5_image: String="",
    val record_type: String="",
    val release_date: String="",
    val title: String="",
    val tracklist: String="",
    val type: String=""
)