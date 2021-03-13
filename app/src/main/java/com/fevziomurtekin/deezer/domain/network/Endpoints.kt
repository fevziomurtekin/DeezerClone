package com.fevziomurtekin.deezer.domain.network

object Endpoints {
    const val GENRE = "/genre"
    const val ARTISTS = "genre/{genreId}/artists"
    const val ARTIST = "artist/{artistId}"
    const val ALBUMS = "artist/{artistId}/albums"
    const val RELATED = "artist/{artistId}/related"
    const val ALBUM = "album/{albumId}/tracks"
    const val SEARCH = "search/album"
}
