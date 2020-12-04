package com.fevziomurtekin.deezer.repository

import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.core.data.DaoResult
import com.fevziomurtekin.deezer.entities.AlbumEntity
import com.fevziomurtekin.deezer.entities.SearchEntity
import kotlinx.coroutines.flow.Flow

interface DeezerRepositoryImpl{
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

    /**
     * give to id return fetching album tracks.
     * @param albumID, String
     * @return Result.Error or Result.Succes(List<AlbumData>)
     * */
    fun fetchAlbumTracks(albumID:String): Flow<ApiResult<List<*>>>

    /**
     * @return List<SearchQuery>?
     * */
    fun fetchRecentSearch() : Flow<ApiResult<List<*>>>

    /**
     * @param query,
     * insert the query.
     * */
    suspend fun insertSearch(query: SearchEntity): DaoResult

    /**
     * @param query, String
     * @return Result.Error or Result.Succes(List<SearchData>)
     * */
    fun fetchSearch(query:String) : Flow<ApiResult<List<*>>>

    /**
     * @param track, AlbumData
     * insert the track on local data.
     * */
    suspend fun insertFavoritesData(track:AlbumEntity?): DaoResult

    /**
     * @return List<Favorites>?
     * */
    fun fetchFavorites() : Flow<ApiResult<List<*>>>

}


