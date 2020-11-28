package com.fevziomurtekin.deezer.data.genre

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
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