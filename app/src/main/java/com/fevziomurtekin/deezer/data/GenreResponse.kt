package com.fevziomurtekin.deezer.data

import com.google.gson.annotations.SerializedName


data class GenreResponse(
    @SerializedName("data")
    val `data`: List<Data>
)

data class Data(
    var id: String,
    var name: String?,
    var picture: String?,
    var picture_big: String?,
    var picture_medium: String?,
    var picture_small: String?,
    var picture_xl: String?,
    var type: String?
)