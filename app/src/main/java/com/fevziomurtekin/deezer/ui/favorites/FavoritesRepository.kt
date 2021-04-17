package com.fevziomurtekin.deezer.ui.favorites

import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.core.data.DataSource
import com.fevziomurtekin.deezer.core.extensions.isSuccessAndNotNull
import com.fevziomurtekin.deezer.core.extensions.letOnFalseOnSuspend
import com.fevziomurtekin.deezer.core.extensions.letOnTrueOnSuspend
import com.fevziomurtekin.deezer.di.IODispatcher
import com.fevziomurtekin.deezer.domain.local.DeezerDao
import com.fevziomurtekin.deezer.entities.AlbumEntity
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

private const val DELAY_TIME = 1500L

class FavoritesRepository @Inject constructor(
    private val deezerDao: DeezerDao,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
): DataSource(), FavoritesRepositoryImpl {

    override fun fetchFavorites()= flow {
        localCallFetch {
            deezerDao.getFavorites()
        }.let { localResult ->
            localResult.isSuccessAndNotNull().letOnTrueOnSuspend {
                Timber.d("getResult : ${Gson().toJson(localResult.data)}")
                emit(ApiResult.Success(localResult.data as List<AlbumEntity>))
            }.letOnFalseOnSuspend {
                /* fake call */
                delay(DELAY_TIME)
                emit(ApiResult.Error(Exception("Unexpected error.")))
            }
        }
    }.flowOn(ioDispatcher)
}



interface FavoritesRepositoryImpl{
    /**
     * @return List<Favorites>?
     * */
    fun fetchFavorites() : Flow<ApiResult<List<*>>>
}
