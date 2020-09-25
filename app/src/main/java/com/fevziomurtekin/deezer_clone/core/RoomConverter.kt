package com.fevziomurtekin.deezer_clone.core

import androidx.room.TypeConverter
import com.fevziomurtekin.deezer_clone.data.genre.Data
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*

object RoomConverter {

    /* --------------------------  Decode Entity Class --------------------------------*/

    @JvmStatic
    @TypeConverter
    fun fromStringToDataResponse(value:String):Data
            = Json.decodeFromString<Data>(value)

    /* --------------------------------  Encode String ----------------------------------*/

    @JvmStatic
    @TypeConverter
    fun fromDataResponseToString(value:Data):String
            = Json.encodeToString(value)

}