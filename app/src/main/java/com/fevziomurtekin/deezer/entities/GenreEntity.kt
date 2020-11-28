package com.fevziomurtekin.deezer.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GenreEntity(
    @PrimaryKey(autoGenerate = true)
    var pId:Long = 0,
    var genreId: String,
    var name: String?,
    var picture: String?,
    var picture_big: String?,
    var picture_medium: String?,
    var picture_small: String?,
    var picture_xl: String?,
    var type: String?
)