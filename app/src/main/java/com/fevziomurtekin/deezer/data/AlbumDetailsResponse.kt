package com.fevziomurtekin.deezer.data

import com.google.gson.annotations.SerializedName

data class AlbumDetailsResponse(
    @SerializedName("data")
    val `data`: List<AlbumData>,
    @SerializedName("total")
    val total: Int
)

data class AlbumData(
    @SerializedName("disk_number") val disk_number: Int,
    @SerializedName("duration") var duration: String,
    @SerializedName("explicit_content_cover") val explicit_content_cover: Int,
    @SerializedName("explicit_content_lyrics") val explicit_content_lyrics: Int,
    @SerializedName("explicit_lyrics") val explicit_lyrics: Boolean,
    @SerializedName("id") var id: String,
    @SerializedName("isrc") val isrc: String="",
    @SerializedName("link") val link: String?="",
    @SerializedName("md5_image") val md5_image: String?="",
    @SerializedName("preview") val preview: String?="",
    @SerializedName("rank") val rank: String?="",
    @SerializedName("readable") val readable: Boolean,
    @SerializedName("title") val title: String?="",
    @SerializedName("title_short") val title_short: String?="",
    @SerializedName("title_version") val title_version: String?="",
    @SerializedName("track_position") val track_position: Int,
    @SerializedName("type") val type: String,
    @SerializedName("fav_time") var fav_time:Long=System.currentTimeMillis(),
    @SerializedName("def_img") val def_img:String="https://www.iconfinder.com/data/icons/social-media-circle-flat-1/1024/itunes-01-01-64.png"
){
    fun durationToTime(){
        val minute = (duration.toInt()) / 60
        val second = (duration.toInt()) % 60
        val sMin = if(minute<10) "0$minute" else minute.toString()
        duration = "$sMin:$second"
    }
}


data class Artist(
    @SerializedName("id") val id: String,
    @SerializedName("link") val link: String,
    @SerializedName("name") val name: String,
    @SerializedName("picture") val picture: String,
    @SerializedName("picture_big") val picture_big: String,
    @SerializedName("picture_medium") val picture_medium: String,
    @SerializedName("picture_small") val picture_small: String,
    @SerializedName("picture_xl") val picture_xl: String,
    @SerializedName("tracklist") val tracklist: String,
    @SerializedName("type") val type: String
)