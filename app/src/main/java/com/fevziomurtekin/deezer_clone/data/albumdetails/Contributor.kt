package com.fevziomurtekin.deezer_clone.data.albumdetails

import kotlinx.serialization.Serializable


@Serializable
data class Contributor(
    val id: Int,
    val link: String,
    val name: String,
    val picture: String,
    val picture_big: String,
    val picture_medium: String,
    val picture_small: String,
    val picture_xl: String,
    val radio: Boolean,
    val role: String,
    val share: String,
    val tracklist: String,
    val type: String
)