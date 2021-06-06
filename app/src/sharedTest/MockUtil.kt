import com.fevziomurtekin.deezer.data.*
import com.fevziomurtekin.deezer.entities.AlbumEntity
import com.fevziomurtekin.deezer.entities.GenreEntity
import com.fevziomurtekin.deezer.entities.SearchEntity

@Suppress("MaxLineLength")
object MockUtil {
    val entity = GenreEntity(
        1L,
        "0",
        "All",
        "https://api.deezer.com/genre/0/image",
        "https://cdns-images.dzcdn.net/images/misc//500x500-000000-80-0-0.jpg",
        "https://cdns-images.dzcdn.net/images/misc//250x250-000000-80-0-0.jpg",
        "https://cdns-images.dzcdn.net/images/misc//56x56-000000-80-0-0.jpg",
        "https://cdns-images.dzcdn.net/images/misc//250x250-000000-80-0-0.jpg",
        "genre"
    )

    val rockEntity = GenreEntity(
        1L,
        "0",
        "Rock",
        "https://api.deezer.com/genre/0/image",
        "https://cdns-images.dzcdn.net/images/misc//500x500-000000-80-0-0.jpg",
        "https://cdns-images.dzcdn.net/images/misc//250x250-000000-80-0-0.jpg",
        "https://cdns-images.dzcdn.net/images/misc//56x56-000000-80-0-0.jpg",
        "https://cdns-images.dzcdn.net/images/misc//250x250-000000-80-0-0.jpg",
        "genre"
    )

    val data = Data(
        "0",
        "All",
        "https://api.deezer.com/genre/0/image",
        "https://cdns-images.dzcdn.net/images/misc//500x500-000000-80-0-0.jpg",
        "https://cdns-images.dzcdn.net/images/misc//250x250-000000-80-0-0.jpg",
        "https://cdns-images.dzcdn.net/images/misc//56x56-000000-80-0-0.jpg",
        "https://cdns-images.dzcdn.net/images/misc//250x250-000000-80-0-0.jpg",
        "genre"
    )

    val genres: List<Data> = listOf(data)

    val genreEntityList: List<GenreEntity> = listOf(entity, entity, rockEntity, entity)

    val recentData = SearchEntity(1L,"ezhel",12345.toLong())

    val searchList: List<SearchEntity> = listOf(recentData)

    val album = AlbumData(
        id="3135553",readable = true,title = "One More Time",titleShort = "One More Time",titleVersion = "",
        isrc = "GBDUW0000053",link = "https://www.deezer.com/track/3135553",duration = "320",trackPosition = 1,
        diskNumber = 1,rank = "880801",explicitLyrics = false,explicitContentLyrics = 0,explicitContentCover = 0,
        preview ="https://cdns-preview-e.dzcdn.net/stream/c-e77d23e0c8ed7567a507a6d1b6a9ca1b-9.mp3",
        md5Image = "2e018122cb56986277102d2041a592c8",
        //artist = Artist("27","Draft Punk","https://api.deezer.com/artist/27/top?limit=50","artist"),
        type = "track"
    )

    val albumEntity = AlbumEntity(
        albumId= "3135553",title = "One More Time",
        albumIsrc = "GBDUW0000053",link = "https://www.deezer.com/track/3135553",duration = "320",
        diskNumber = 1,explicitLyrics = false,md5Image = "2e018122cb56986277102d2041a592c8",
        //artist = Artist("27","Draft Punk","https://api.deezer.com/artist/27/top?limit=50","artist"),
        type = "track"
    )

    val artist = ArtistData(
        id="8354140",
        name="Ezhel",
        picture = "https://api.deezer.com/artist/8354140/image",
        pictureSmall = "https://e-cdns-images.dzcdn.net/images/artist/07b69699f7402e13504714ec6dedd99f/56x56-000000-80-0-0.jpg",
        pictureMedium = "https://e-cdns-images.dzcdn.net/images/artist/07b69699f7402e13504714ec6dedd99f/250x250-000000-80-0-0.jpg",
        pictureBig = "https://e-cdns-images.dzcdn.net/images/artist/07b69699f7402e13504714ec6dedd99f/500x500-000000-80-0-0.jpg",
        pictureXl = "https://e-cdns-images.dzcdn.net/images/artist/07b69699f7402e13504714ec6dedd99f/1000x1000-000000-80-0-0.jpg",
        radio = true,tracklist = "https://api.deezer.com/artist/8354140/top?limit=50",type = "artist"
    )

    val artistAlbum = ArtistAlbumData(
        id = "51174732",title = "Müptezhel" ,link = "https://www.deezer.com/album/51174732",
        cover = "https://api.deezer.com/album/51174732/image",
        coverSmall = "https://e-cdns-images.dzcdn.net/images/cover/35d715873d3e8ae76a998f6bf38e1fa8/56x56-000000-80-0-0.jpg",
        coverMedium = "https://e-cdns-images.dzcdn.net/images/cover/35d715873d3e8ae76a998f6bf38e1fa8/250x250-000000-80-0-0.jpg",
        coverBig = "https://e-cdns-images.dzcdn.net/images/cover/35d715873d3e8ae76a998f6bf38e1fa8/500x500-000000-80-0-0.jpg",
        coverXl = "https://e-cdns-images.dzcdn.net/images/cover/35d715873d3e8ae76a998f6bf38e1fa8/1000x1000-000000-80-0-0.jpg",
        md5Image = "35d715873d3e8ae76a998f6bf38e1fa8",genreId = 116, fans = 17707, releaseDate = "2017-05-25", recordType = "album",
        tracklist = "https://api.deezer.com/album/51174732/tracks", explicitLyrics = true, type = "album")

    val artistDetails = ArtistDetailResponse(
        id="8354140",name="Ezhel",picture = "https://api.deezer.com/artist/8354140/image",
        pictureSmall = "https://e-cdns-images.dzcdn.net/images/artist/07b69699f7402e13504714ec6dedd99f/56x56-000000-80-0-0.jpg",
        pictureMedium = "https://e-cdns-images.dzcdn.net/images/artist/07b69699f7402e13504714ec6dedd99f/250x250-000000-80-0-0.jpg",
        pictureBig = "https://e-cdns-images.dzcdn.net/images/artist/07b69699f7402e13504714ec6dedd99f/500x500-000000-80-0-0.jpg",
        pictureXl = "https://e-cdns-images.dzcdn.net/images/artist/07b69699f7402e13504714ec6dedd99f/1000x1000-000000-80-0-0.jpg",
        radio = true,tracklist = "https://api.deezer.com/artist/8354140/top?limit=50",type = "artist",link ="https://www.deezer.com/artist/8354140",
        nbAlbum = 20,nbFan =565833 ,share ="https://www.deezer.com/artist/8354140?utm_source=deezer&utm_content=artist-8354140&utm_term=0_1601505695&utm_medium=web"
    )

    val artistRelatedData = ArtistRelatedData(
        id="8354140",name="Ezhel",picture = "https://api.deezer.com/artist/8354140/image",
        pictureSmall = "https://e-cdns-images.dzcdn.net/images/artist/07b69699f7402e13504714ec6dedd99f/56x56-000000-80-0-0.jpg",
        pictureMedium = "https://e-cdns-images.dzcdn.net/images/artist/07b69699f7402e13504714ec6dedd99f/250x250-000000-80-0-0.jpg",
        pictureBig = "https://e-cdns-images.dzcdn.net/images/artist/07b69699f7402e13504714ec6dedd99f/500x500-000000-80-0-0.jpg",
        pictureXl = "https://e-cdns-images.dzcdn.net/images/artist/07b69699f7402e13504714ec6dedd99f/1000x1000-000000-80-0-0.jpg",
        radio = true,tracklist = "https://api.deezer.com/artist/8354140/top?limit=50",type = "artist",link ="https://www.deezer.com/artist/8354140",
        nbAlbum = 20,nbFan =565833
    )

    val searchData = SearchData(
        id = "51174732",title = "Müptezhel" ,link = "https://www.deezer.com/album/51174732",
        cover = "https://api.deezer.com/album/51174732/image",
        coverSmall = "https://e-cdns-images.dzcdn.net/images/cover/35d715873d3e8ae76a998f6bf38e1fa8/56x56-000000-80-0-0.jpg",
        coverMedium = "https://e-cdns-images.dzcdn.net/images/cover/35d715873d3e8ae76a998f6bf38e1fa8/250x250-000000-80-0-0.jpg",
        coverBig = "https://e-cdns-images.dzcdn.net/images/cover/35d715873d3e8ae76a998f6bf38e1fa8/500x500-000000-80-0-0.jpg",
        coverXl = "https://e-cdns-images.dzcdn.net/images/cover/35d715873d3e8ae76a998f6bf38e1fa8/1000x1000-000000-80-0-0.jpg",
        md5Image = "35d715873d3e8ae76a998f6bf38e1fa8",genreId = 116, recordType = "album",nbTracks = 20,
        artist = Artist(
            id="8354140",name="Ezhel",picture = "https://api.deezer.com/artist/8354140/image",
            pictureSmall = "https://e-cdns-images.dzcdn.net/images/artist/07b69699f7402e13504714ec6dedd99f/56x56-000000-80-0-0.jpg",
            pictureMedium = "https://e-cdns-images.dzcdn.net/images/artist/07b69699f7402e13504714ec6dedd99f/250x250-000000-80-0-0.jpg",
            pictureBig = "https://e-cdns-images.dzcdn.net/images/artist/07b69699f7402e13504714ec6dedd99f/500x500-000000-80-0-0.jpg",
            pictureXl = "https://e-cdns-images.dzcdn.net/images/artist/07b69699f7402e13504714ec6dedd99f/1000x1000-000000-80-0-0.jpg",
            tracklist = "https://api.deezer.com/artist/8354140/top?limit=50",
            type = "artist",
            link ="https://www.deezer.com/artist/8354140",
        ), tracklist = "https://api.deezer.com/album/51174732/tracks",
        explicitLyrics = true,
        type = "album"
    )

    const val genreID = "116"
    const val artistID = "8354140"
    const val albumID = "51174732"
    const val query = "Ezhel"
}
