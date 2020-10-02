package com.fevziomurtekin.deezer_clone.core

import androidx.room.TypeConverter
import com.fevziomurtekin.deezer_clone.data.albumdetails.AlbumData
import com.fevziomurtekin.deezer_clone.data.genre.Data
import com.fevziomurtekin.deezer_clone.data.search.SearchQuery
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*

object RoomConverter {

    /* --------------------------  Decode Entity Class --------------------------------*/

    @JvmStatic
    @TypeConverter
    fun fromStringToDataResponse(value:String):Data
            = Json.decodeFromString<Data>(value)


    @JvmStatic
    @TypeConverter
    fun fromStringToSearchQueryResponse(value:String):SearchQuery
            = Json.decodeFromString<SearchQuery>(value)

    @JvmStatic
    @TypeConverter
    fun fromStringToFavoriteResponse(value:String):AlbumData
            = Json.decodeFromString(value)



    /* --------------------------------  Encode String ----------------------------------*/

    @JvmStatic
    @TypeConverter
    fun fromDataResponseToString(value:Data):String
            = Json.encodeToString(value)

    @JvmStatic
    @TypeConverter
    fun fromSearchQueryResponseToString(value:SearchQuery):String
            = Json.encodeToString(value)

    @JvmStatic
    @TypeConverter
    fun fromFavoritesResponseToString(value:AlbumData):String
            = Json.encodeToString(value)

}