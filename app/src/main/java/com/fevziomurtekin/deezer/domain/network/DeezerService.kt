package com.fevziomurtekin.deezer.domain.network

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
            :Deferred<GenreResponse>

    @GET("genre/{genreId}/artists")
    fun fetchArtistList(@Path("genreId") genreId:String)
            :Deferred<ArtistsResponse>

    @GET("artist/{artistId}")
    fun fetchArtistDetails(@Path("artistId") artistID:String)
            :Deferred<ArtistDetailResponse>

    @GET("artist/{artistId}/albums")
    fun fetchArtistAlbums(@Path("artistId") artistID: String)
            :Deferred<ArtistAlbumResponse>

    @GET("artist/{artistId}/related")
    fun fetchArtistRelated(@Path("artistId") artistID: String)
            :Deferred<ArtistRelatedResponse>

    @GET("album/{albumId}/tracks")
    fun fetchAlbumDetails(@Path("albumId") albumId:String)
            :Deferred<AlbumDetailsResponse>

    @GET("search/album")
    fun fetchSearchAlbum(@Query("q") q:String)
            :Deferred<SearchResponse>
}