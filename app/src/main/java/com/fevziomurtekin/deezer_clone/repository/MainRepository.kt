package com.fevziomurtekin.deezer_clone.repository

import com.fevziomurtekin.deezer_clone.domain.local.DeezerDao
import com.fevziomurtekin.deezer_clone.domain.network.DeezerService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import com.fevziomurtekin.deezer_clone.core.Result
import timber.log.Timber
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val deezerService: DeezerService,
    private val deezerDao: DeezerDao
) {

    fun fetchGenreList(
        page: Int
    ) = flow {
        emit( Result.Loading )
        /*var characters = rmDao.getCharacterList(page)
        Timber.d("characters : $characters - ${characters.size}")
        if(characters.isNullOrEmpty()){
            val response = rmService.fetchCharacters(page).await()
            Timber.d("response : $response")
            if(!response.results.isNullOrEmpty()){
                characters = response.results
                characters.forEach { character -> character.page = page }
                rmDao.insertRMCharacterList(characters)
                emit(Result.Succes(characters))
            }else{ emit(Result.Error) }
        }else{ emit(Result.Succes(characters)) }*/
    }.flowOn(Dispatchers.IO)
}