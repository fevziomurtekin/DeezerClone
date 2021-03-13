package com.fevziomurtekin.deezer.core

import timber.log.Timber

object TimberFactory {
    fun setupOnDebug(){
        Timber.plant(Timber.DebugTree())
    }
}
