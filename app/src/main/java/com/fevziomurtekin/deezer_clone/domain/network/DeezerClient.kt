package com.fevziomurtekin.deezer_clone.domain.network

import javax.inject.Inject

class DeezerClient @Inject constructor(
        private val deezerService: DeezerService
){

    suspend fun fetchGenreList()
            = deezerService.fetchGenreList()

    suspend fun fetchArtistList(genreId:String)
            = deezerService.fetchArtistList(genreId)

    suspend fun fetchArtistDetails(artistID: String)
            = deezerService.fetchArtistDetails(artistID)

    fun fetchArtistAlbums(artistID: String)
            = deezerService.fetchArtistAlbums(artistID)

    fun fetchArtistRelated(artistID: String)
            = deezerService.fetchArtistRelated(artistID)

    fun fetchAlbumDetails(albumID:String)
            = deezerService.fetchAlbumDetails(albumID)

    fun fetchSearchAlbum(q:String)
            = deezerService.fetchSearchAlbum(q)
}