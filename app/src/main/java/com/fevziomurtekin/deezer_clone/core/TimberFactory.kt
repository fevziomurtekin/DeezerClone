package com.fevziomurtekin.deezer_clone.core

import androidx.viewbinding.BuildConfig
import timber.log.Timber

object TimberFactory {
    fun setupOnDebug(){
        Timber.plant(Timber.DebugTree())
    }
}