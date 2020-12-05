package com.fevziomurtekin.deezer.ui.album

import com.fevziomurtekin.deezer.core.data.ApiCallback
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.core.data.DaoResult
import com.fevziomurtekin.deezer.core.extensions.getResult
import com.fevziomurtekin.deezer.core.extensions.isSuccessAndNotNull
import com.fevziomurtekin.deezer.core.extensions.letOnFalseOnSuspend
import com.fevziomurtekin.deezer.core.extensions.letOnTrueOnSuspend
import com.fevziomurtekin.deezer.data.AlbumData
import com.fevziomurtekin.deezer.data.AlbumDetailsResponse
import com.fevziomurtekin.deezer.domain.local.DeezerDao
import com.fevziomurtekin.deezer.domain.network.DeezerClient
import com.fevziomurtekin.deezer.entities.AlbumEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

class AlbumRepository(
    val deezerClient: DeezerClient,
    val deezerDao: DeezerDao
): ApiCallback(), AlbumRepositoryImpl {

    override fun fetchAlbumTracks(albumID:String
    ) = flow {
        emit(ApiResult.Loading)
        networkCall {
            deezerClient.fetchAlbumDetails(albumID)
        }.let { apiResult ->
            apiResult.isSuccessAndNotNull().letOnTrueOnSuspend {
                Timber.d("fetchAlbumTracks apiResult : ${apiResult.getResult() }")
                Timber.d("fetchAlbumTracks apiResult : ${(apiResult.getResult() as AlbumDetailsResponse).data}")
                val response = (apiResult.getResult() as AlbumDetailsResponse).data
                response.forEach { it.durationToTime() }
                emit(ApiResult.Success(response))
            }.letOnFalseOnSuspend {
                /* fake call */
                delay(1500)
                emit(ApiResult.Error(Exception("Unexpected error.")))
            }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun insertFavoritesData(track: AlbumEntity?) = localCallInsert {
        track?.let {
            deezerDao.insertTrack(it)
        }
    }

}


interface AlbumRepositoryImpl {
    /**
     * give to id return fetching album tracks.
     * @param albumID, String
     * @return Result.Error or Result.Succes(List<AlbumData>)
     * */
    fun fetchAlbumTracks(albumID:String): Flow<ApiResult<List<AlbumData>>>


    /**
     * @param track, AlbumData
     * insert the track on local data.
     * */
    suspend fun insertFavoritesData(track:AlbumEntity?): DaoResult


}