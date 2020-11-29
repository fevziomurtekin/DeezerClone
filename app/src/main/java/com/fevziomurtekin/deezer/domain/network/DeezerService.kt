package com.fevziomurtekin.deezer.domain.network

import com.fevziomurtekin.deezer.core.data.ApiResponse
import com.fevziomurtekin.deezer.data.AlbumDetailsResponse
import com.fevziomurtekin.deezer.data.ArtistsResponse
import com.fevziomurtekin.deezer.data.ArtistAlbumResponse
import com.fevziomurtekin.deezer.data.ArtistDetailResponse
import com.fevziomurtekin.deezer.data.ArtistRelatedResponse
import com.fevziomurtekin.deezer.data.GenreResponse
import com.fevziomurtekin.deezer.data.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface DeezerService{

    @GET(DeezerEndpoints.GENRE)
    suspend fun fetchGenreList()
            :ApiResponse<GenreResponse>

    @GET(DeezerEndpoints.ARTISTS)
    suspend fun fetchArtistList(@Path("genreId") genreId:String)
            :ApiResponse<ArtistsResponse>

    @GET(DeezerEndpoints.ARTIST)
    suspend fun fetchArtistDetails(@Path("artistId") artistID:String)
            :ApiResponse<ArtistDetailResponse>

    @GET(DeezerEndpoints.ALBUMS)
    suspend fun fetchArtistAlbums(@Path("artistId") artistID: String)
            :ApiResponse<ArtistAlbumResponse>

    @GET(DeezerEndpoints.RELATED)
    suspend fun fetchArtistRelated(@Path("artistId") artistID: String)
            :ApiResponse<ArtistRelatedResponse>

    @GET(DeezerEndpoints.ALBUM)
    suspend fun fetchAlbumDetails(@Path("albumId") albumId:String)
            :ApiResponse<AlbumDetailsResponse>

    @GET(DeezerEndpoints.SEARCH)
    suspend fun fetchSearchAlbum(@Query("q") q:String)
            :ApiResponse<SearchResponse>
}