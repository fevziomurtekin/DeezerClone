package com.fevziomurtekin.deezer_clone.data.genre

import androidx.room.Entity
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class Data(
    val id: String,
    val name: String,
    val picture: String,
    val picture_big: String,
    val picture_medium: String,
    val picture_small: String,
    val picture_xl: String,
    val type: String
)