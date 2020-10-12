package com.fevziomurtekin.deezer.domain.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fevziomurtekin.deezer.core.Env
import com.fevziomurtekin.deezer.core.RoomConverter
import com.fevziomurtekin.deezer.data.albumdetails.AlbumData
import com.fevziomurtekin.deezer.data.genre.Data
import com.fevziomurtekin.deezer.data.search.SearchQuery

@Database(entities = [Data::class,SearchQuery::class,AlbumData::class], version = Env.DATABASE_VERSION, exportSchema = false)
@TypeConverters(value = [RoomConverter::class])
abstract class DeezerDatabase : RoomDatabase(){
    abstract fun deezerDao() : DeezerDao
}