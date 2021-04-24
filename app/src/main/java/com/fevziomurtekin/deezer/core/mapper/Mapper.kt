package com.fevziomurtekin.deezer.core.mapper

import com.fevziomurtekin.deezer.data.AlbumData
import com.fevziomurtekin.deezer.data.Data
import com.fevziomurtekin.deezer.entities.AlbumEntity
import com.fevziomurtekin.deezer.entities.GenreEntity
import timber.log.Timber


private val EMPTY_ALBUM_DATA: AlbumData = AlbumData(
    0,"",0,0,false,"","","","","","",false,"","","",
    0,"",0L,""
)

private val EMPTY_ALBUM_ENTITY: AlbumEntity = AlbumEntity(
    0,"",false,1L,"","","","","","",1L,""
)

private val EMPTY_GENRE_DATA: Data = Data(
    "", "", "", "", "", "", "", ""
)

private val EMPTY_GENRE_ENTITY = GenreEntity(
    genreId = "",
    name= "",
    picture= "",
    pictureBig= "",
    pictureMedium= "",
    pictureSmall= "",
    pictureXl= "",
    type= ""
)

fun AlbumEntity?.mapper(): AlbumData = this?.let { e->
    AlbumData(
        diskNumber= e.diskNumber,
        duration= e.duration,
        explicitContentCover = 0,
        explicitLyrics = e.explicitLyrics,
        id = e.albumId,
        isrc = e.albumIsrc,
        explicitContentLyrics = 0,
        readable = true,
        trackPosition = 0,
        type = e.type,
    )
} ?: EMPTY_ALBUM_DATA

fun AlbumData?.mapper(): AlbumEntity = this?.let { d->
    AlbumEntity(
        diskNumber= d.diskNumber,
        duration= d.duration,
        explicitLyrics = d.explicitLyrics,
        albumId = d.id,
        albumIsrc= d.isrc,
        link = d.link,
        md5Image = d.md5Image,
        title = d.title,
        type = d.type,
        favTime = d.favTime,
    )
} ?: EMPTY_ALBUM_ENTITY

fun GenreEntity?.mapper(): Data = this?.let { e->
    Data(
        id = e.genreId,
        name= e.name,
        picture= e.picture,
        pictureBig= e.pictureBig,
        pictureMedium= e.pictureMedium,
        pictureSmall= e.pictureSmall,
        pictureXl= e.pictureXl,
        type= "genre"
    )
} ?: EMPTY_GENRE_DATA

fun Data?.mapper(): GenreEntity = this?.let { d->
    GenreEntity(
        genreId = d.id,
        name= d.name,
        picture= d.picture,
        pictureBig= d.pictureBig,
        pictureMedium= d.pictureMedium,
        pictureSmall= d.pictureSmall,
        pictureXl= d.pictureXl,
        type= d.pictureXl
    )
} ?: EMPTY_GENRE_ENTITY


@JvmName("genreMapper")
fun List<GenreEntity?>?.mapper(): List<Data> = this?.let { list ->
    list.map { it.mapper() }
} ?: emptyList()

@JvmName("dataMapper")
fun List<Data>?.mapper(): List<GenreEntity> = this?.let { list ->
    list.map { it.mapper() }
} ?: emptyList()

@JvmName("albumEntityToAlbumData")
fun List<AlbumEntity>.mapper(): List<AlbumData> {
    return this.map { it.mapper()  }
}

