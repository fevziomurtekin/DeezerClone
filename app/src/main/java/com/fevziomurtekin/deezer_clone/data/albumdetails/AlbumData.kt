package com.fevziomurtekin.deezer_clone.data.albumdetails

import kotlinx.serialization.Serializable

@Serializable
data class AlbumData(
    val artist: Artist,
    val disk_number: Int,
    var duration: String,
    val explicit_content_cover: Int,
    val explicit_content_lyrics: Int,
    val explicit_lyrics: Boolean,
    val id: String?="",
    val isrc: String?="",
    val link: String?="",
    val md5_image: String?="",
    val preview: String?="",
    val rank: String?="",
    val readable: Boolean,
    val title: String?="",
    val title_short: String?="",
    val title_version: String?="",
    val track_position: Int,
    val type: String,
    val def_img:String="https://www.iconfinder.com/data/icons/social-media-circle-flat-1/1024/itunes-01-01-64.png"
){
    fun durationToTime(){
        val minute = (duration.toInt()) / 60
        val second = (duration.toInt()) % 60
        val sMin = if(minute<10) "0$minute" else minute.toString()
        duration = "$sMin:$second"
    }
}