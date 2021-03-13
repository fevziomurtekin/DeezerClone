package com.fevziomurtekin.deezer.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Suppress("LongParameterList")
@Entity
class AlbumEntity(
    val diskNumber: Int,
    var duration: String,
    val explicitLyrics: Boolean,
    @PrimaryKey(autoGenerate = true)
    var pId: Long = 1,
    var albumId: String,
    val albumIsrc: String="",
    val link: String?="",
    val md5Image: String?="",
    val title: String?="",
    val type: String,
    var favTime:Long=System.currentTimeMillis(),
    val defImg:String="https://www.iconfinder.com/data/icons/social-media-circle-flat-1/1024/itunes-01-01-64.png"
)
