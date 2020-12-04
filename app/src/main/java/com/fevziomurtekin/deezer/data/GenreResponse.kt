package com.fevziomurtekin.deezer.data

import com.google.gson.annotations.SerializedName


data class GenreResponse(
    @SerializedName("data")
    val `data`: List<Data>
)

data class Data(
    @SerializedName("id") var id: String,
    @SerializedName("name") var name: String?,
    @SerializedName("picture") var picture: String?,
    @SerializedName("picture_big") var picture_big: String?,
    @SerializedName("picture_medium") var picture_medium: String?,
    @SerializedName("picture_small") var picture_small: String?,
    @SerializedName("picture_xl") var picture_xl: String?,
    @SerializedName("type") var type: String?
)