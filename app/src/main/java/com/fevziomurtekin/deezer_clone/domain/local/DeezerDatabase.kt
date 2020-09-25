package com.fevziomurtekin.deezer_clone.domain.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fevziomurtekin.deezer_clone.core.Env
import com.fevziomurtekin.deezer_clone.domain.local.DeezerDao
import com.fevziomurtekin.deezer_clone.core.RoomConverter
import com.fevziomurtekin.deezer_clone.data.genre.Data

@Database(entities = [Data::class], version = Env.DATABASE_VERSION, exportSchema = false)
@TypeConverters(value = [RoomConverter::class])
abstract class DeezerDatabase : RoomDatabase(){
    abstract fun deezerDao() : DeezerDao
}