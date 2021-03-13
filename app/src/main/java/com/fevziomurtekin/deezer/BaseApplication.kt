package com.fevziomurtekin.deezer

import android.app.Application
import com.fevziomurtekin.deezer.core.TimberFactory
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        TimberFactory.setupOnDebug()
    }
}

