package com.fevziomurtekin.deezer.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier


@Module
@InstallIn(SingletonComponent::class)
object CoroutineDispatcherModule {

    @DefaultDispatcher
    @Provides
    internal fun provideDefaultDispatcher(): CoroutineDispatcher
        = Dispatchers.Default

    @IODispatcher
    @Provides
    internal fun provideIODispatcher(): CoroutineDispatcher
            = Dispatchers.IO

    @MainDispatcher
    @Provides
    internal fun provideMainDispatcher(): CoroutineDispatcher
            = Dispatchers.Main

}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IODispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher
