package com.fevziomurtekin.deezer.core.mapper

import androidx.room.TypeConverter
import com.fevziomurtekin.deezer.data.albumdetails.AlbumData
import com.fevziomurtekin.deezer.data.genre.Data
import com.fevziomurtekin.deezer.entities.AlbumEntity
import com.fevziomurtekin.deezer.entities.GenreEntity
import com.fevziomurtekin.deezer.entities.SearchEntity
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*

object RoomConverter {

    /* --------------------------  Decode Entity Class --------------------------------*/

    @JvmStatic
    @TypeConverter
    fun fromStringToGenreEntity(value:String):GenreEntity
            = Json.decodeFromString<GenreEntity>(value)


    @JvmStatic
    @TypeConverter
    fun fromStringToSearchEntity(value:String): SearchEntity
            = Json.decodeFromString<SearchEntity>(value)

    @JvmStatic
    @TypeConverter
    fun fromStringToAlbumEntity(value:String):AlbumEntity
            = Json.decodeFromString(value)



    /* --------------------------------  Encode String ----------------------------------*/

    @JvmStatic
    @TypeConverter
    fun fromGenreEntityToString(value:GenreEntity):String
            = Json.encodeToString(value)

    @JvmStatic
    @TypeConverter
    fun fromSearchEntityToString(value: SearchEntity):String
            = Json.encodeToString(value)

    @JvmStatic
    @TypeConverter
    fun fromAlbumEntityToString(value: AlbumEntity):String
            = Json.encodeToString(value)

}