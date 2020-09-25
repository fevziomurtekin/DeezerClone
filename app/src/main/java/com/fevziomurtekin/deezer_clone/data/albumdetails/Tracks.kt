package com.fevziomurtekin.deezer_clone.data.albumdetails

import kotlinx.serialization.Serializable

@Serializable
data class Tracks(
    val `data`: List<DataX>
)