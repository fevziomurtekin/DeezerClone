package com.fevziomurtekin.deezer.ui.favorites

import com.fevziomurtekin.deezer.core.data.ApiCallback
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.core.extensions.isSuccessAndNotNull
import com.fevziomurtekin.deezer.core.extensions.letOnFalseOnSuspend
import com.fevziomurtekin.deezer.core.extensions.letOnTrueOnSuspend
import com.fevziomurtekin.deezer.domain.local.DeezerDao
import com.fevziomurtekin.deezer.domain.network.DeezerClient
import com.fevziomurtekin.deezer.entities.AlbumEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FavoritesRepository(
    val deezerClient: DeezerClient,
    val deezerDao: DeezerDao
): ApiCallback(), FavoritesRepositoryImpl {

    override fun fetchFavorites()= flow {
        localCallFetch {
            deezerDao.getFavorites()
        }.let { localResult ->
            localResult.isSuccessAndNotNull().letOnTrueOnSuspend {
                emit(ApiResult.Success(localResult.data as List<AlbumEntity>))
            }.letOnFalseOnSuspend {
                /* fake call */
                delay(1500)
                emit(ApiResult.Error(Exception("Unexpected error.")))
            }
        }
    }.flowOn(Dispatchers.IO)
}



interface FavoritesRepositoryImpl{
    /**
     * @return List<Favorites>?
     * */
    fun fetchFavorites() : Flow<ApiResult<List<*>>>
}