package com.fevziomurtekin.deezer.data

import com.google.gson.annotations.SerializedName

private const val DEFAULT_MINUTE= 10
private const val SECOND_MINUTE= 60

data class AlbumDetailsResponse(
    @SerializedName("data")
    val data: List<AlbumData>,
    @SerializedName("total")
    val total: Int
)

data class AlbumData(
    @SerializedName("disk_number") val diskNumber: Int,
    @SerializedName("duration") var duration: String,
    @SerializedName("explicit_content_cover") val explicitContentCover: Int,
    @SerializedName("explicit_content_lyrics") val explicitContentLyrics: Int,
    @SerializedName("explicit_lyrics") val explicitLyrics: Boolean,
    @SerializedName("id") var id: String,
    @SerializedName("isrc") val isrc: String="",
    @SerializedName("link") val link: String?="",
    @SerializedName("md5_image") val md5Image: String?="",
    @SerializedName("preview") val preview: String?="",
    @SerializedName("rank") val rank: String?="",
    @SerializedName("readable") val readable: Boolean,
    @SerializedName("title") val title: String?="",
    @SerializedName("title_short") val titleShort: String?="",
    @SerializedName("title_version") val titleVersion: String?="",
    @SerializedName("track_position") val trackPosition: Int,
    @SerializedName("type") val type: String,
    @SerializedName("fav_time") var favTime:Long=System.currentTimeMillis(),
    @SerializedName("def_img") val defImg:String =
        "https://www.iconfinder.com/data/icons/social-media-circle-flat-1/1024/itunes-01-01-64.png"
){
    fun durationToTime(){
        val minute = (duration.toInt()) / SECOND_MINUTE
        val second = (duration.toInt()) % SECOND_MINUTE
        val sMin = if(minute< DEFAULT_MINUTE) "0$minute" else minute.toString()
        duration = "$sMin:$second"
    }
}


data class Artist(
    @SerializedName("id") val id: String,
    @SerializedName("link") val link: String,
    @SerializedName("name") val name: String,
    @SerializedName("picture") val picture: String,
    @SerializedName("picture_big") val pictureBig: String,
    @SerializedName("picture_medium") val pictureMedium: String,
    @SerializedName("picture_small") val pictureSmall: String,
    @SerializedName("picture_xl") val pictureXl: String,
    @SerializedName("tracklist") val tracklist: String,
    @SerializedName("type") val type: String
)

