package com.fevziomurtekin.deezer.core

import com.fevziomurtekin.deezer.data.albumdetails.AlbumData
import com.fevziomurtekin.deezer.data.artist.ArtistData
import com.fevziomurtekin.deezer.data.artistdetails.ArtistAlbumData
import com.fevziomurtekin.deezer.data.artistdetails.ArtistDetailResponse
import com.fevziomurtekin.deezer.data.artistdetails.ArtistRelatedData
import com.fevziomurtekin.deezer.data.genre.Data
import com.fevziomurtekin.deezer.data.search.SearchData
import com.fevziomurtekin.deezer.entities.SearchEntity

object MockUtil {
    val data = Data("0",
        "All",
        "https://api.deezer.com/genre/0/image",
        "https://cdns-images.dzcdn.net/images/misc//500x500-000000-80-0-0.jpg",
        "https://cdns-images.dzcdn.net/images/misc//250x250-000000-80-0-0.jpg",
        "https://cdns-images.dzcdn.net/images/misc//56x56-000000-80-0-0.jpg",
        "https://cdns-images.dzcdn.net/images/misc//250x250-000000-80-0-0.jpg",
        "genre"
    )

    val genres = listOf(data)

    val recentData = SearchEntity("1","ezhel",12345.toLong())

    val searchList = listOf(recentData)

    val album = AlbumData(
        id="3135553",readable = true,title = "One More Time",title_short = "One More Time",title_version = "",
        isrc = "GBDUW0000053",link = "https://www.deezer.com/track/3135553",duration = "320",track_position = 1,
        disk_number = 1,rank = "880801",explicit_lyrics = false,explicit_content_lyrics = 0,explicit_content_cover = 0,
        preview ="https://cdns-preview-e.dzcdn.net/stream/c-e77d23e0c8ed7567a507a6d1b6a9ca1b-9.mp3",md5_image = "2e018122cb56986277102d2041a592c8",
        //artist = Artist("27","Draft Punk","https://api.deezer.com/artist/27/top?limit=50","artist"),
        type = "track"
    )

    val artist = ArtistData(
        id="8354140",name="Ezhel",picture = "https://api.deezer.com/artist/8354140/image",
        picture_small = "https://e-cdns-images.dzcdn.net/images/artist/07b69699f7402e13504714ec6dedd99f/56x56-000000-80-0-0.jpg",
        picture_medium = "https://e-cdns-images.dzcdn.net/images/artist/07b69699f7402e13504714ec6dedd99f/250x250-000000-80-0-0.jpg",
        picture_big = "https://e-cdns-images.dzcdn.net/images/artist/07b69699f7402e13504714ec6dedd99f/500x500-000000-80-0-0.jpg",
        picture_xl = "https://e-cdns-images.dzcdn.net/images/artist/07b69699f7402e13504714ec6dedd99f/1000x1000-000000-80-0-0.jpg",
        radio = true,tracklist = "https://api.deezer.com/artist/8354140/top?limit=50",type = "artist"
    )

    val artistAlbum = ArtistAlbumData(
        id = "51174732",title = "Müptezhel" ,link = "https://www.deezer.com/album/51174732",
        cover = "https://api.deezer.com/album/51174732/image",
        cover_small = "https://e-cdns-images.dzcdn.net/images/cover/35d715873d3e8ae76a998f6bf38e1fa8/56x56-000000-80-0-0.jpg",
        cover_medium = "https://e-cdns-images.dzcdn.net/images/cover/35d715873d3e8ae76a998f6bf38e1fa8/250x250-000000-80-0-0.jpg",
        cover_big = "https://e-cdns-images.dzcdn.net/images/cover/35d715873d3e8ae76a998f6bf38e1fa8/500x500-000000-80-0-0.jpg",
        cover_xl = "https://e-cdns-images.dzcdn.net/images/cover/35d715873d3e8ae76a998f6bf38e1fa8/1000x1000-000000-80-0-0.jpg",
        md5_image = "35d715873d3e8ae76a998f6bf38e1fa8",genre_id = 116, fans = 17707, release_date = "2017-05-25", record_type = "album",
        tracklist = "https://api.deezer.com/album/51174732/tracks", explicit_lyrics = true, type = "album")

    val artistDetails = ArtistDetailResponse(
        id="8354140",name="Ezhel",picture = "https://api.deezer.com/artist/8354140/image",
        picture_small = "https://e-cdns-images.dzcdn.net/images/artist/07b69699f7402e13504714ec6dedd99f/56x56-000000-80-0-0.jpg",
        picture_medium = "https://e-cdns-images.dzcdn.net/images/artist/07b69699f7402e13504714ec6dedd99f/250x250-000000-80-0-0.jpg",
        picture_big = "https://e-cdns-images.dzcdn.net/images/artist/07b69699f7402e13504714ec6dedd99f/500x500-000000-80-0-0.jpg",
        picture_xl = "https://e-cdns-images.dzcdn.net/images/artist/07b69699f7402e13504714ec6dedd99f/1000x1000-000000-80-0-0.jpg",
        radio = true,tracklist = "https://api.deezer.com/artist/8354140/top?limit=50",type = "artist",link ="https://www.deezer.com/artist/8354140",
        nb_album = 20,nb_fan =565833 ,share ="https://www.deezer.com/artist/8354140?utm_source=deezer&utm_content=artist-8354140&utm_term=0_1601505695&utm_medium=web"
    )

    val artistRelatedData = ArtistRelatedData(
        id="8354140",name="Ezhel",picture = "https://api.deezer.com/artist/8354140/image",
        picture_small = "https://e-cdns-images.dzcdn.net/images/artist/07b69699f7402e13504714ec6dedd99f/56x56-000000-80-0-0.jpg",
        picture_medium = "https://e-cdns-images.dzcdn.net/images/artist/07b69699f7402e13504714ec6dedd99f/250x250-000000-80-0-0.jpg",
        picture_big = "https://e-cdns-images.dzcdn.net/images/artist/07b69699f7402e13504714ec6dedd99f/500x500-000000-80-0-0.jpg",
        picture_xl = "https://e-cdns-images.dzcdn.net/images/artist/07b69699f7402e13504714ec6dedd99f/1000x1000-000000-80-0-0.jpg",
        radio = true,tracklist = "https://api.deezer.com/artist/8354140/top?limit=50",type = "artist",link ="https://www.deezer.com/artist/8354140",
        nb_album = 20,nb_fan =565833
    )

    val searchData = SearchData(
        id = "51174732",title = "Müptezhel" ,link = "https://www.deezer.com/album/51174732",
        cover = "https://api.deezer.com/album/51174732/image",
        cover_small = "https://e-cdns-images.dzcdn.net/images/cover/35d715873d3e8ae76a998f6bf38e1fa8/56x56-000000-80-0-0.jpg",
        cover_medium = "https://e-cdns-images.dzcdn.net/images/cover/35d715873d3e8ae76a998f6bf38e1fa8/250x250-000000-80-0-0.jpg",
        cover_big = "https://e-cdns-images.dzcdn.net/images/cover/35d715873d3e8ae76a998f6bf38e1fa8/500x500-000000-80-0-0.jpg",
        cover_xl = "https://e-cdns-images.dzcdn.net/images/cover/35d715873d3e8ae76a998f6bf38e1fa8/1000x1000-000000-80-0-0.jpg",
        md5_image = "35d715873d3e8ae76a998f6bf38e1fa8",genre_id = 116, record_type = "album",nb_tracks = 20,
        artist = com.fevziomurtekin.deezer.data.search.Artist(
            id="8354140",name="Ezhel",picture = "https://api.deezer.com/artist/8354140/image",
            picture_small = "https://e-cdns-images.dzcdn.net/images/artist/07b69699f7402e13504714ec6dedd99f/56x56-000000-80-0-0.jpg",
            picture_medium = "https://e-cdns-images.dzcdn.net/images/artist/07b69699f7402e13504714ec6dedd99f/250x250-000000-80-0-0.jpg",
            picture_big = "https://e-cdns-images.dzcdn.net/images/artist/07b69699f7402e13504714ec6dedd99f/500x500-000000-80-0-0.jpg",
            picture_xl = "https://e-cdns-images.dzcdn.net/images/artist/07b69699f7402e13504714ec6dedd99f/1000x1000-000000-80-0-0.jpg",
            tracklist = "https://api.deezer.com/artist/8354140/top?limit=50",type = "artist",link ="https://www.deezer.com/artist/8354140",
        ), tracklist = "https://api.deezer.com/album/51174732/tracks", explicit_lyrics = true, type = "album"
    )

    val genreID = "116"
    val artistID = "8354140"
    val albumID = "51174732"
    val query = "Ezhel"
}