package com.fevziomurtekin.deezer_clone.domain.network

import com.fevziomurtekin.deezer_clone.data.genre.GenreResponse
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query


interface DeezerService{

    @GET("api/character")
    fun fetchGenreList(
            @Query("page") page:Int =1
    ):Deferred<GenreResponse>

}