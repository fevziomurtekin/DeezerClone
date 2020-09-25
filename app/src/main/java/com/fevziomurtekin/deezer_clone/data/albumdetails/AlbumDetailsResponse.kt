package com.fevziomurtekin.deezer_clone.data.albumdetails

import kotlinx.serialization.Serializable

@Serializable
data class AlbumDetailsResponse(
    val artist: Artist,
    val available: Boolean,
    val contributors: List<Contributor>,
    val cover: String,
    val cover_big: String,
    val cover_medium: String,
    val cover_small: String,
    val cover_xl: String,
    val duration: Int,
    val explicit_content_cover: Int,
    val explicit_content_lyrics: Int,
    val explicit_lyrics: Boolean,
    val fans: Int,
    val genre_id: Int,
    val genres: Genres,
    val id: String,
    val label: String,
    val link: String,
    val md5_image: String,
    val nb_tracks: Int,
    val rating: Int,
    val record_type: String,
    val release_date: String,
    val share: String,
    val title: String,
    val tracklist: String,
    val tracks: Tracks,
    val type: String,
    val upc: String
)