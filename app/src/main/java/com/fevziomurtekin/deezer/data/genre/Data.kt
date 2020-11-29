package com.fevziomurtekin.deezer.data.genre

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class Data(
    @PrimaryKey
    @SerialName("id")
    var genreId: String,
    var name: String?,
    var picture: String?,
    var picture_big: String?,
    var picture_medium: String?,
    var picture_small: String?,
    var picture_xl: String?,
    var type: String?
)