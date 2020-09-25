package com.fevziomurtekin.deezer_clone.data.artistdetails

import kotlinx.serialization.SerialName


data class ArtistAlbumResponse(
    @SerialName("data")
    val `data`: List<ArtistAlbumData>,
    val next: String,
    val total: Int
)