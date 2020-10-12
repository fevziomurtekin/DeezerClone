package com.fevziomurtekin.deezer.core

import com.fevziomurtekin.deezer.data.albumdetails.AlbumData
import com.fevziomurtekin.deezer.data.search.SearchQuery
import kotlinx.coroutines.flow.Flow

abstract class BaseRepository{

    /**
     * give to id return fetching genreList list.
     * @return Result.Error or Result.Succes(List<Data>)
     * */
    abstract suspend fun fetchGenreList(): Flow<Result<List<*>>>
    /**
     * give to id return fetching artist list.
     * @param genreID, String
     * @return Result.Error or Result.Succes(List<ArtistData>)
     * */
    abstract fun fetchArtistList(genreID:String): Flow<Result<List<*>>>

    /**
     * give to id return fetching artist details.
     * @param artistID, String
     * @return Result.Error or Result.Succes(List<ArtistData>)
     * */
    abstract fun fetchArtistDetails(artistID:String):Flow<Result<*>>

    /**
     * give to id return fetching artist albums.
     * @param artistID, artistId
     * @return Result.Error or Result.Succes(List<ArtistAlbumData>)
     * */
    abstract fun fetchArtistAlbums(artistID:String): Flow<Result<List<*>>>

    /**
     * give to id return fetching artist related.
     * @param artistID, String
     * @return Result.Error or Result.Succes(List<ArtistRelatedData>)
     * */
    abstract fun fetchArtistRelated(artistID: String): Flow<Result<List<*>>>

    /**
     * give to id return fetching album tracks.
     * @param albumID, String
     * @return Result.Error or Result.Succes(List<AlbumData>)
     * */
    abstract fun fetchAlbumTracks(albumID:String): Flow<Result<List<*>>>

    /**
     * @return List<SearchQuery>?
     * */
    abstract fun fetchRecentSearch() : Flow<List<*>>

    /**
     * @param query,
     * insert the query.
     * */
    abstract suspend fun insertSearch(query: SearchQuery)

    /**
     * @param query, String
     * @return Result.Error or Result.Succes(List<SearchData>)
     * */
    abstract fun fetchSearch(query:String) : Flow<Result<List<*>>>

    /**
     * @param track, AlbumData
     * insert the track on local data.
     * */
    abstract suspend fun insertFavoritesData(track:AlbumData)

    /**
     * @return List<Favorites>?
     * */
    abstract fun fetchFavorites() : Flow<List<*>>

}


