package com.fevziomurtekin.deezer.di

import android.app.Application
import androidx.room.Room
import com.fevziomurtekin.deezer.core.Env
import com.fevziomurtekin.deezer.domain.local.DeezerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule{

    @Provides
    @Singleton
    fun providesDeezerDatabase(application: Application)
        = Room.databaseBuilder(application, DeezerDatabase::class.java, Env.DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun providesDeezerDao(deezerDatabase: DeezerDatabase)
        = deezerDatabase.deezerDao()
}
