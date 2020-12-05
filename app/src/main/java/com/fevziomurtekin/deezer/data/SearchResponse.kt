package com.fevziomurtekin.deezer.data

import com.google.gson.annotations.SerializedName


data class SearchResponse(
    @SerializedName("data")
    val `data`: List<SearchData>,
    @SerializedName("next")
    val next: String,
    @SerializedName("total")
    val total: Int
)

data class SearchData(
    @SerializedName("artist") val artist: SearchArtist,
    @SerializedName("cover") val cover: String,
    @SerializedName("cover_big") val cover_big: String,
    @SerializedName("cover_medium") val cover_medium: String,
    @SerializedName("cover_small") val cover_small: String,
    @SerializedName("cover_xl") val cover_xl: String,
    @SerializedName("explicit_lyrics") val explicit_lyrics: Boolean,
    @SerializedName("genre_id") val genre_id: Int,
    @SerializedName("id") val id: String,
    @SerializedName("link") val link: String,
    @SerializedName("md5_image") val md5_image: String,
    @SerializedName("nb_tracks") val nb_tracks: Int,
    @SerializedName("record_type") val record_type: String,
    @SerializedName("title") val title: String,
    @SerializedName("tracklist") val tracklist: String,
    @SerializedName("type") val type: String
)


data class SearchArtist(
    @SerializedName("id") val artist: Long,
    @SerializedName("name") val name: String,
    @SerializedName("link") val link: String,
    @SerializedName("picture_medium") val picture_medium: String,
    @SerializedName("tracklist") val tracklist: String,
    @SerializedName("cover_xl") val cover_xl: String,
    @SerializedName("type") val type: String,
)