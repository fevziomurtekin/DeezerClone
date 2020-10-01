package com.fevziomurtekin.deezer_clone.repository

import androidx.annotation.WorkerThread
import com.fevziomurtekin.deezer_clone.domain.local.DeezerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import com.fevziomurtekin.deezer_clone.core.Result
import com.fevziomurtekin.deezer_clone.data.search.SearchQuery
import com.fevziomurtekin.deezer_clone.domain.network.DeezerClient
import kotlinx.coroutines.delay
import timber.log.Timber
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val deezerClient: DeezerClient,
    private val deezerDao: DeezerDao
) {

    /**
     * fetching genres list.
     *  @return Result.Error or Result.Succes(Data)
     */
    suspend fun fetchGenreList(
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

    /**
     * give to id return fetching artist list.
     * @param genreId, String
     * @return Result.Error or Result.Succes(List<ArtistData>)
     * */
    fun fetchArtistList( genreId:String
    ) = flow {
        emit( Result.Loading )
        val response = deezerClient.fetchArtistList(genreId).await()
        Timber.d("response : $response")
        if(!response.artistData.isNullOrEmpty()){
            emit(Result.Succes(response.artistData))
        }else{
            /* fake call */
            delay(1500)
            emit(Result.Error)
        }
    }.flowOn(Dispatchers.IO)

    /**
     * give to id return fetching artist details.
     * @param artistId, String
     * @return Result.Error or Result.Succes(List<ArtistData>)
     * */

    fun fetchArtistDetails( artistId:String
    ) = flow {
        emit( Result.Loading )
        val response = deezerClient.fetchArtistDetails(artistId).await()
        Timber.d("response : $response")
        if(response!=null){
            emit(Result.Succes(response))
        }else{
            /* fake call */
            delay(1500)
            emit(Result.Error)
        }
    }.flowOn(Dispatchers.IO)

    /**
     * give to id return fetching artist albums.
     * @param id, artistId
     * @return Result.Error or Result.Succes(List<ArtistAlbumData>)
     * */

    fun fetchArtistAlbums( artistId:String
    ) = flow {
        emit( Result.Loading )
        val response = try{deezerClient.fetchArtistAlbums(artistId).await()}catch (e:Exception){ null }

        try{deezerClient.fetchArtistAlbums(artistId).await()}catch (e:Exception){ Timber.d("eror : ${e.message}") }

        Timber.d("response : $response")
        if(response!=null){
            emit(Result.Succes(response.data))
        }else{
            /* fake call */
            delay(1500)
            emit(Result.Error)
        }
    }.flowOn(Dispatchers.IO)

    /**
     * give to id return fetching artist related.
     * @param artistId, String
     * @return Result.Error or Result.Succes(List<ArtistRelatedData>)
     * */

    fun fetchArtistRelated( artistId:String
    ) = flow {
        emit( Result.Loading )
        val response = deezerClient.fetchArtistRelated(artistId).await()
        Timber.d("response : $response")
        if(response!=null){
            emit(Result.Succes(response.data))
        }else{
            /* fake call */
            delay(1500)
            emit(Result.Error)
        }
    }.flowOn(Dispatchers.IO)

    /**
     * give to id return fetching album tracks.
     * @param albumID, String
     * @return Result.Error or Result.Succes(List<AlbumData>)
     * */

    fun fetchAlbumTracks(albumID:String
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

    /**
     * @return List<SearchQuery>?
     * */
    fun fetchRecentSearch()= flow {
        Timber.d(" --------- fetchRecentSearch ---------")
        val response = deezerDao.getQueryList()
        Timber.d("response : $response")
        if(!response.isNullOrEmpty()){ emit(response) }
    }.flowOn(Dispatchers.IO)


    /**
     * @param query,
     * insert the query.
     * */
    suspend fun insertSearch(query: SearchQuery)= deezerDao.insertQuery(query)

    /**
     * @param query, String
     * @return Result.Error or Result.Succes(List<SearchData>)
     * */
    fun fetchSearch(query:String) = flow{
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


}