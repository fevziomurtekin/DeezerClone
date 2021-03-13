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
    var pictureBig: String?,
    var pictureMedium: String?,
    var pictureSmall: String?,
    var pictureXl: String?,
    var type: String?
)
