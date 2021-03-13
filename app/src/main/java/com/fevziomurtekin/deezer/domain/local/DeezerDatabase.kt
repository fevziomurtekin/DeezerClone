package com.fevziomurtekin.deezer.domain.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fevziomurtekin.deezer.core.Env
import com.fevziomurtekin.deezer.core.mapper.RoomConverter
import com.fevziomurtekin.deezer.entities.AlbumEntity
import com.fevziomurtekin.deezer.entities.GenreEntity
import com.fevziomurtekin.deezer.entities.SearchEntity

@Database(
    entities = [GenreEntity::class, SearchEntity::class,AlbumEntity::class],
    version = Env.DATABASE_VERSION, exportSchema = false
)
@TypeConverters(value = [RoomConverter::class])
abstract class DeezerDatabase : RoomDatabase(){
    abstract fun deezerDao() : DeezerDao
}
