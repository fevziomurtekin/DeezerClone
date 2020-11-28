package com.fevziomurtekin.deezer.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
class SearchEntity (
    @PrimaryKey(autoGenerate = true)
    var searchId: Long = 0L,
    var q: String?="",
    var time: Long=System.currentTimeMillis()
)
