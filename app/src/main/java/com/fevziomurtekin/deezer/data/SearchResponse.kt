package com.fevziomurtekin.deezer.data

import com.google.gson.annotations.SerializedName


data class SearchResponse(
    @SerializedName("data")
    val `data`: List<SearchData>,
    val next: String,
    val total: Int
)

data class SearchData(
    val artist: Artist,
    val cover: String,
    val cover_big: String,
    val cover_medium: String,
    val cover_small: String,
    val cover_xl: String,
    val explicit_lyrics: Boolean,
    val genre_id: Int,
    val id: String,
    val link: String,
    val md5_image: String,
    val nb_tracks: Int,
    val record_type: String,
    val title: String,
    val tracklist: String,
    val type: String
)


