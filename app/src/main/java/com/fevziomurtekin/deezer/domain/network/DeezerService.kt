package com.fevziomurtekin.deezer.domain.network

import com.fevziomurtekin.deezer.data.ArtistAlbumResponse
import com.fevziomurtekin.deezer.data.ArtistDetailResponse
import com.fevziomurtekin.deezer.data.ArtistsResponse
import com.fevziomurtekin.deezer.data.GenreResponse
import com.fevziomurtekin.deezer.data.AlbumDetailsResponse
import com.fevziomurtekin.deezer.data.ArtistRelatedResponse
import com.fevziomurtekin.deezer.data.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface DeezerService{

    @GET(Endpoints.GENRE)
    suspend fun fetchGenreList()
            : Response<GenreResponse>

    @GET(Endpoints.ARTISTS)
    suspend fun fetchArtistList(@Path("genreId") genreId:String)
            : Response<ArtistsResponse>

    @GET(Endpoints.ARTIST)
    suspend fun fetchArtistDetails(@Path("artistId") artistID:String)
            : Response<ArtistDetailResponse>

    @GET(Endpoints.ALBUMS)
    suspend fun fetchArtistAlbums(@Path("artistId") artistID: String)
            : Response<ArtistAlbumResponse>

    @GET(Endpoints.RELATED)
    suspend fun fetchArtistRelated(@Path("artistId") artistID: String)
            : Response<ArtistRelatedResponse>

    @GET(Endpoints.ALBUM)
    suspend fun fetchAlbumDetails(@Path("albumId") albumId:String)
            : Response<AlbumDetailsResponse>

    @GET(Endpoints.SEARCH)
    suspend fun fetchSearchAlbum(@Query("q") q:String)
            : Response<SearchResponse>

}
