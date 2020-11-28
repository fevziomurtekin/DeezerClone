package com.fevziomurtekin.deezer.domain.network

import com.fevziomurtekin.deezer.core.data.ApiResponse
import com.fevziomurtekin.deezer.data.albumdetails.AlbumDetailsResponse
import com.fevziomurtekin.deezer.data.artist.ArtistsResponse
import com.fevziomurtekin.deezer.data.artistdetails.ArtistAlbumResponse
import com.fevziomurtekin.deezer.data.artistdetails.ArtistDetailResponse
import com.fevziomurtekin.deezer.data.artistdetails.ArtistRelatedResponse
import com.fevziomurtekin.deezer.data.genre.GenreResponse
import com.fevziomurtekin.deezer.data.search.SearchResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface DeezerService{

    @GET("genre")
    fun fetchGenreList()
            :ApiResponse<GenreResponse>

    @GET("genre/{genreId}/artists")
    fun fetchArtistList(@Path("genreId") genreId:String)
            :ApiResponse<ArtistsResponse>

    @GET("artist/{artistId}")
    fun fetchArtistDetails(@Path("artistId") artistID:String)
            :ApiResponse<ArtistDetailResponse>

    @GET("artist/{artistId}/albums")
    fun fetchArtistAlbums(@Path("artistId") artistID: String)
            :ApiResponse<ArtistAlbumResponse>

    @GET("artist/{artistId}/related")
    fun fetchArtistRelated(@Path("artistId") artistID: String)
            :ApiResponse<ArtistRelatedResponse>

    @GET("album/{albumId}/tracks")
    fun fetchAlbumDetails(@Path("albumId") albumId:String)
            :ApiResponse<AlbumDetailsResponse>

    @GET("search/album")
    fun fetchSearchAlbum(@Query("q") q:String)
            :ApiResponse<SearchResponse>
}