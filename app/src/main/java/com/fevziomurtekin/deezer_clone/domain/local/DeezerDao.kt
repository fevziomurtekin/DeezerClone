package com.fevziomurtekin.deezer_clone.domain.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fevziomurtekin.deezer_clone.data.genre.Data

@Dao
interface DeezerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenreList(genreList:List<Data>)

    @Query("SELECT * FROM Data")
    suspend fun getGenreList():List<Data>
}