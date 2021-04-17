package com.fevziomurtekin.deezer.ui.artist

import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.core.data.DataSource
import com.fevziomurtekin.deezer.core.extensions.getResult
import com.fevziomurtekin.deezer.core.extensions.isSuccessAndNotNull
import com.fevziomurtekin.deezer.core.extensions.letOnFalseOnSuspend
import com.fevziomurtekin.deezer.core.extensions.letOnTrueOnSuspend
import com.fevziomurtekin.deezer.data.ArtistAlbumResponse
import com.fevziomurtekin.deezer.data.ArtistDetailResponse
import com.fevziomurtekin.deezer.data.ArtistRelatedResponse
import com.fevziomurtekin.deezer.data.ArtistsResponse
import com.fevziomurtekin.deezer.di.IODispatcher
import com.fevziomurtekin.deezer.domain.network.DeezerClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

private const val FAKE_DELAY_TIME = 1500L

class ArtistRepository @Inject constructor(
    private val deezerClient: DeezerClient,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
): DataSource(), ArtistRepositoryImpl {

    override fun fetchArtistList(genreID:String)
            = flow {
        emit(ApiResult.Loading)
        networkCall {
            deezerClient.fetchArtistList(genreID)
        }.let { apiResult ->
            apiResult.isSuccessAndNotNull().letOnTrueOnSuspend {
                emit(
                    ApiResult.Success(
                        (apiResult.getResult() as ArtistsResponse)
                            .artistData
                    )
                )
            }.letOnFalseOnSuspend {
                delay(FAKE_DELAY_TIME)
                emit(ApiResult.Error(Exception("Unexpected error.")))
            }
        }
    }.flowOn(ioDispatcher)


    override fun fetchArtistDetails(artistID:String
    ) = flow {
        emit( ApiResult.Loading )
        networkCall {
            deezerClient.fetchArtistDetails(artistID)
        }.let { apiResult ->
            Timber.d("fetchArtistDetails - api result : $apiResult")
            apiResult.isSuccessAndNotNull().letOnTrueOnSuspend {
                emit(ApiResult.Success(apiResult.getResult() as ArtistDetailResponse))
            }.letOnTrueOnSuspend {
                /* fake call */
                delay(FAKE_DELAY_TIME)
                emit(ApiResult.Error(Exception("Unexpected error.")))
            }
        }
    }.flowOn(ioDispatcher)


    override fun fetchArtistAlbums(artistID:String
    ) = flow {
        emit( ApiResult.Loading )
        networkCall {
            deezerClient.fetchArtistAlbums(artistID)
        }.let { apiResult ->
            apiResult.isSuccessAndNotNull().letOnTrueOnSuspend {
                Timber.d("fetchArtistAlbums api result : ${apiResult.getResult()}")
                emit(ApiResult.Success((apiResult.getResult() as ArtistAlbumResponse).data))
            }.letOnFalseOnSuspend {
                /* fake call */
                delay(FAKE_DELAY_TIME)
                emit(ApiResult.Error(Exception("Unexpected error.")))
            }
        }
    }.flowOn(ioDispatcher)



    override fun fetchArtistRelated(artistID:String
    ) = flow {
        emit( ApiResult.Loading )
        networkCall {
            deezerClient.fetchArtistRelated(artistID)
        }.let { apiResult ->
            Timber.d("fetchArtistRelated - api result : $apiResult")
            apiResult.isSuccessAndNotNull().letOnTrueOnSuspend {
                emit(
                    ApiResult.Success(
                        (apiResult.getResult() as ArtistRelatedResponse).data
                    )
                )
            }.letOnFalseOnSuspend {
                /* fake call */
                delay(FAKE_DELAY_TIME)
                emit(ApiResult.Error(Exception("Unexpected error.")))
            }
        }
    }.flowOn(ioDispatcher)

}


interface ArtistRepositoryImpl {
    /**
     * give to id return fetching artist list.
     * @param genreID, String
     * @return Result.Error or Result.Succes(List<ArtistData>)
     * */
    fun fetchArtistList(genreID:String): Flow<ApiResult<*>>

    /**
     * give to id return fetching artist details.
     * @param artistID, String
     * @return Result.Error or Result.Succes(List<ArtistData>)
     * */
    fun fetchArtistDetails(artistID:String):Flow<ApiResult<*>>

    /**
     * give to id return fetching artist albums.
     * @param artistID, artistId
     * @return Result.Error or Result.Succes(List<ArtistAlbumData>)
     * */
    fun fetchArtistAlbums(artistID:String): Flow<ApiResult<List<*>>>

    /**
     * give to id return fetching artist related.
     * @param artistID, String
     * @return Result.Error or Result.Succes(List<ArtistRelatedData>)
     * */
    fun fetchArtistRelated(artistID: String): Flow<ApiResult<List<*>>>

}
