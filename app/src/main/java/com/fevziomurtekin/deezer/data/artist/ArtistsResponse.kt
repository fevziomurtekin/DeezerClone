package com.fevziomurtekin.deezer.data.artist

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtistsResponse(
    @SerialName("data")
    val artistData: List<ArtistData>
)