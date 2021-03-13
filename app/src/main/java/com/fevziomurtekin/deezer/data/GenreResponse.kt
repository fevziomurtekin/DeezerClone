package com.fevziomurtekin.deezer.data

import com.google.gson.annotations.SerializedName


data class GenreResponse(
    @SerializedName("data")
    val data: List<Data>
)

data class Data(
    @SerializedName("id") var id: String,
    @SerializedName("name") var name: String?,
    @SerializedName("picture") var picture: String?,
    @SerializedName("picture_big") var pictureBig: String?,
    @SerializedName("picture_medium") var pictureMedium: String?,
    @SerializedName("picture_small") var pictureSmall: String?,
    @SerializedName("picture_xl") var pictureXl: String?,
    @SerializedName("type") var type: String?
)
