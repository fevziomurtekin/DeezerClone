package com.fevziomurtekin.deezer_clone.data.albumdetails

import kotlinx.serialization.Serializable

@Serializable
data class DataX(
    val artist: ArtistX,
    val duration: String,
    val explicit_content_cover: Int,
    val explicit_content_lyrics: Int,
    val explicit_lyrics: Boolean,
    val id: String,
    val link: String,
    val md5_image: String,
    val preview: String,
    val rank: String,
    val readable: Boolean,
    val title: String,
    val title_short: String,
    val title_version: String,
    val type: String
)