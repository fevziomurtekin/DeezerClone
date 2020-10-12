package com.fevziomurtekin.deezer.repository

import com.fevziomurtekin.deezer.core.BaseRepository
import com.fevziomurtekin.deezer.domain.local.DeezerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import com.fevziomurtekin.deezer.core.Result
import com.fevziomurtekin.deezer.data.albumdetails.AlbumData
import com.fevziomurtekin.deezer.data.search.SearchQuery
import com.fevziomurtekin.deezer.domain.network.DeezerClient
import kotlinx.coroutines.delay
import timber.log.Timber
import javax.inject.Inject

class DeezerRepository @Inject constructor(
    private val deezerClient: DeezerClient,
    private val deezerDao: DeezerDao
) : BaseRepository() {

    override suspend fun fetchGenreList() = flow {
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


    override fun fetchArtistList(genreID:String
    ) = flow {
        emit( Result.Loading )
        val response = deezerClient.fetchArtistList(genreID).await()
        Timber.d("response : $response")
        if(!response.artistData.isNullOrEmpty()){
            emit(Result.Succes(response.artistData))
        }else{
            /* fake call */
            delay(1500)
            emit(Result.Error)
        }
    }.flowOn(Dispatchers.IO)



    override fun fetchArtistDetails(artistID:String
    ) = flow {
        emit( Result.Loading )
        val response = deezerClient.fetchArtistDetails(artistID).await()
        Timber.d("response : $response")
        if(response!=null){
            emit(Result.Succes(response))
        }else{
            /* fake call */
            delay(1500)
            emit(Result.Error)
        }
    }.flowOn(Dispatchers.IO)


    override fun fetchArtistAlbums(artistID:String
    ) = flow {
        emit( Result.Loading )
        val response = try{deezerClient.fetchArtistAlbums(artistID).await()}catch (e:Exception){ null }

        try{deezerClient.fetchArtistAlbums(artistID).await()}catch (e:Exception){ Timber.d("eror : ${e.message}") }

        Timber.d("response : $response")
        if(response!=null){
            emit(Result.Succes(response.data))
        }else{
            /* fake call */
            delay(1500)
            emit(Result.Error)
        }
    }.flowOn(Dispatchers.IO)



    override fun fetchArtistRelated(artistID:String
    ) = flow {
        emit( Result.Loading )
        val response = deezerClient.fetchArtistRelated(artistID).await()
        Timber.d("response : $response")
        if(response!=null){
            emit(Result.Succes(response.data))
        }else{
            /* fake call */
            delay(1500)
            emit(Result.Error)
        }
    }.flowOn(Dispatchers.IO)


    override fun fetchAlbumTracks(albumID:String
    ) = flow {
        emit( Result.Loading )
        val response = deezerClient.fetchAlbumDetails(albumID).await()
        Timber.d("response : $response")
        if(response!=null){
            response.data.forEach { it.durationToTime() }
            emit(Result.Succes(response.data))
        }else{
            /* fake call */
            delay(1500)
            emit(Result.Error)
        }   
    }.flowOn(Dispatchers.IO)


    override fun fetchRecentSearch()= flow {
        Timber.d(" --------- fetchRecentSearch ---------")
        val response = deezerDao.getQueryList()
        Timber.d("response : $response")
        if(!response.isNullOrEmpty()){ emit(response) }
    }.flowOn(Dispatchers.IO)


    override suspend fun insertSearch(query: SearchQuery)= deezerDao.insertQuery(query)


    override fun fetchSearch(query:String) = flow{
        Timber.d(" --------- fetchSearch ---------")
        emit(Result.Loading)
        insertSearch(SearchQuery(q=query))
        val response = deezerClient.fetchSearchAlbum(query).await()
        Timber.d("response : $response")
        if(response!=null){
            emit(Result.Succes(response.data))
        }else{
            /* fake call */
            delay(1500)
            emit(Result.Error)
        }
    }.flowOn(Dispatchers.IO)


    override suspend fun insertFavoritesData(track:AlbumData) = deezerDao.insertTrack(track)


    override fun fetchFavorites()= flow {
        Timber.d(" --------- fetchFavorites ---------")
        val response = deezerDao.getFavorites()
        Timber.d("response : $response")
        if(!response.isNullOrEmpty()){ emit(response) }
    }.flowOn(Dispatchers.IO)

}