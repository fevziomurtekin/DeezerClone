package com.fevziomurtekin.deezer_clone.domain.network

import javax.inject.Inject

class DeezerClient @Inject constructor(
        private val rmService: DeezerService
){

    suspend fun fetchGenreList(
            page:Int
    ) = rmService.fetchGenreList(page)


}