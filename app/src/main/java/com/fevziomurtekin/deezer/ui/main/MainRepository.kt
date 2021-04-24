package com.fevziomurtekin.deezer.ui.main

import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.core.data.DataSource
import com.fevziomurtekin.deezer.core.extensions.getResult
import com.fevziomurtekin.deezer.core.extensions.isSuccessAndNotNull
import com.fevziomurtekin.deezer.core.extensions.letOnFalseOnSuspend
import com.fevziomurtekin.deezer.core.extensions.letOnTrueOnSuspend
import com.fevziomurtekin.deezer.core.mapper.mapper
import com.fevziomurtekin.deezer.data.GenreResponse
import com.fevziomurtekin.deezer.di.IODispatcher
import com.fevziomurtekin.deezer.domain.local.DeezerDao
import com.fevziomurtekin.deezer.domain.network.DeezerClient
import com.fevziomurtekin.deezer.entities.GenreEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

private const val FAKE_DELAY_TIME = 1500L

class MainRepository @Inject constructor(
    private val deezerClient: DeezerClient,
    private val deezerDao: DeezerDao,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
): DataSource(), MainRepositoryImpl {

    override suspend fun fetchGenreList() = flow {
        emit(ApiResult.Loading)
        localCallFetch {
            deezerDao.getGenreList()
        }.let { localResult ->
            localResult.isSucces.letOnFalseOnSuspend {
                networkCall {
                    deezerClient.fetchGenreList()
                }.let { apiResult->
                    apiResult.isSuccessAndNotNull().letOnTrueOnSuspend {
                        (apiResult.getResult() as? GenreResponse)?.data?.let {
                            localCallInsert { deezerDao.insertGenreList(it.mapper()) }
                            emit(ApiResult.Success(it))
                        } ?: run {  emit(ApiResult.Error(TypeCastException("unkown error."))) }
                    }
                }
            }.letOnTrueOnSuspend {
                val result = (localResult.data as? List<GenreEntity>)?.mapper()
                delay(FAKE_DELAY_TIME)
                emit(ApiResult.Success(result?.toList()))
            }
        }
    }.flowOn(ioDispatcher)
}


interface MainRepositoryImpl {
    /**
     * give to id return fetching genreList list.
     * @return Result.Error or Result.Succes(List<Data>)
     * */
    suspend fun fetchGenreList(): Flow<ApiResult<*>>
}

