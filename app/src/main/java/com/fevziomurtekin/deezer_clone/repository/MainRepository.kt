package com.fevziomurtekin.deezer_clone.repository

import com.fevziomurtekin.deezer_clone.domain.local.DeezerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import com.fevziomurtekin.deezer_clone.core.Result
import com.fevziomurtekin.deezer_clone.domain.network.DeezerClient
import kotlinx.coroutines.delay
import timber.log.Timber
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val deezerClient: DeezerClient,
    private val deezerDao: DeezerDao
) {

    fun fetchGenreList(
    ) = flow {
        emit( Result.Loading )
        var genres = deezerDao.getGenreList()
        Timber.d("genres: $genres - ${genres.size}")
        if(genres.isNullOrEmpty()){
            val response = deezerClient.fetchGenreList().await()
            Timber.d("response : $response")
            if(!response.data.isNullOrEmpty()){
                genres = response.data
                deezerDao.insertGenreList(genres)
                emit(Result.Succes(genres))
            }else{
                /* fake call */
                delay(1500)
                emit(Result.Error)
            }
        }else{
            /* fake call */
            delay(1500)
            emit(Result.Succes(genres))
        }
    }.flowOn(Dispatchers.IO)
}