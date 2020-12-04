package com.fevziomurtekin.deezer.ui.main

import com.fevziomurtekin.deezer.core.data.ApiCallback
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.core.extensions.getResult
import com.fevziomurtekin.deezer.core.extensions.isSuccessAndNotNull
import com.fevziomurtekin.deezer.core.extensions.letOnFalseOnSuspend
import com.fevziomurtekin.deezer.core.extensions.letOnTrueOnSuspend
import com.fevziomurtekin.deezer.core.mapper
import com.fevziomurtekin.deezer.data.Data
import com.fevziomurtekin.deezer.data.GenreResponse
import com.fevziomurtekin.deezer.domain.local.DeezerDao
import com.fevziomurtekin.deezer.domain.network.DeezerClient
import com.fevziomurtekin.deezer.entities.GenreEntity
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val deezerClient: DeezerClient,
    private val deezerDao: DeezerDao
): ApiCallback(), MainRepositoryImpl {

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
                delay(1500)
                emit(ApiResult.Success(result?.toList()))
            }
        }
    }.flowOn(Dispatchers.IO)

}

