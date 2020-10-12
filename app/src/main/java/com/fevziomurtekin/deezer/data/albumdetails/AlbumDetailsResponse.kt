package com.fevziomurtekin.deezer.data.albumdetails

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlbumDetailsResponse(
    @SerialName("data")
    val `data`: List<AlbumData>,
    val total: Int
)