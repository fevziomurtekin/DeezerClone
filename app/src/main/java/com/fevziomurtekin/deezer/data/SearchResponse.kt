package com.fevziomurtekin.deezer.data

import com.google.gson.annotations.SerializedName


data class SearchResponse(
    @SerializedName("data") val data: List<SearchData>,
    @SerializedName("next") val next: String,
    @SerializedName("total") val total: Int
)

data class SearchData(
    @SerializedName("artist") var artist: Artist,
    @SerializedName("cover") val cover: String,
    @SerializedName("cover_big") val coverBig: String,
    @SerializedName("cover_medium") val coverMedium: String,
    @SerializedName("cover_small") val coverSmall: String,
    @SerializedName("cover_xl") val coverXl: String,
    @SerializedName("explicit_lyrics") val explicitLyrics: Boolean,
    @SerializedName("genre_id") val genreId: Int,
    @SerializedName("id") val id: String,
    @SerializedName("link") val link: String,
    @SerializedName("md5_image") val md5Image: String,
    @SerializedName("nb_tracks") val nbTracks: Int,
    @SerializedName("record_type") val recordType: String,
    @SerializedName("title") val title: String,
    @SerializedName("tracklist") val tracklist: String,
    @SerializedName("type") val type: String
)


