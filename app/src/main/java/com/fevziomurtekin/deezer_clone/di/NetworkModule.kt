package com.fevziomurtekin.deezer_clone.di

import androidx.room.Insert
import com.fevziomurtekin.deezer_clone.core.Env
import com.fevziomurtekin.deezer_clone.core.HttpRequestInterceptor
import com.fevziomurtekin.deezer_clone.domain.network.DeezerClient
import com.fevziomurtekin.deezer_clone.domain.network.DeezerService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient():OkHttpClient
            = OkHttpClient.Builder()
            .addInterceptor(HttpRequestInterceptor())
            .build()

    @ExperimentalSerializationApi
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Env.BASE_URL)
            .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

    @Provides
    @Singleton
    fun provideDeezerService(retrofit:Retrofit) = retrofit.create(DeezerService::class.java)

    @Provides
    @Singleton
    fun provideDeezerClient(deezerService: DeezerService) = DeezerClient(deezerService)
}