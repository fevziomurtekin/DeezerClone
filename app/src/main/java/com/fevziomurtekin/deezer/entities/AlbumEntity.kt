package com.fevziomurtekin.deezer.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class AlbumEntity(
    val disk_number: Int,
    var duration: String,
    val explicit_lyrics: Boolean,
    @PrimaryKey(autoGenerate = true)
    var pId: Long = 0,
    var albumId: String,
    val albumIsrc: String="",
    val link: String?="",
    val md5_image: String?="",
    val title: String?="",
    val type: String,
    var fav_time:Long=System.currentTimeMillis(),
    val def_img:String="https://www.iconfinder.com/data/icons/social-media-circle-flat-1/1024/itunes-01-01-64.png"
)