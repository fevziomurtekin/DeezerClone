package com.fevziomurtekin.deezer.ui.main

import com.fevziomurtekin.deezer.core.data.ApiResult
import kotlinx.coroutines.flow.Flow

interface MainRepositoryImpl {
    /**
     * give to id return fetching genreList list.
     * @return Result.Error or Result.Succes(List<Data>)
     * */
    suspend fun fetchGenreList(): Flow<ApiResult<*>>
}