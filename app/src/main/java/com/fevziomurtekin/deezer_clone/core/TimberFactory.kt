package com.fevziomurtekin.deezer_clone.core

import timber.log.Timber

object TimberFactory {
    fun setupOnDebug(){
        Timber.plant(Timber.DebugTree())
    }
}