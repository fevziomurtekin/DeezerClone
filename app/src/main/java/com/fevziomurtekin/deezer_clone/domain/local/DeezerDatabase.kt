package com.fevziomurtekin.deezer_clone.domain.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fevziomurtekin.deezer_clone.core.Env
import com.fevziomurtekin.deezer_clone.core.RoomConverter
import com.fevziomurtekin.deezer_clone.data.genre.Data
import com.fevziomurtekin.deezer_clone.data.search.SearchQuery

@Database(entities = [Data::class,SearchQuery::class], version = Env.DATABASE_VERSION, exportSchema = false)
@TypeConverters(value = [RoomConverter::class])
abstract class DeezerDatabase : RoomDatabase(){
    abstract fun deezerDao() : DeezerDao
}