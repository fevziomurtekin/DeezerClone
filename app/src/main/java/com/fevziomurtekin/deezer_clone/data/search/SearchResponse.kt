package com.fevziomurtekin.deezer_clone.data.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    @SerialName("data")
    val `data`: List<SearchData>,
    val next: String,
    val total: Int
)