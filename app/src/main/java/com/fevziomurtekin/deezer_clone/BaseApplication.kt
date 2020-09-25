package com.fevziomurtekin.deezer_clone

import android.app.Application
import com.fevziomurtekin.deezer_clone.core.TimberFactory
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        TimberFactory.setupOnDebug()
    }
}