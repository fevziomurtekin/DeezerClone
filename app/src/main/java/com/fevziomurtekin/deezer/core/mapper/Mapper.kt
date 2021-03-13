package com.fevziomurtekin.deezer.core

import com.fevziomurtekin.deezer.data.AlbumData
import com.fevziomurtekin.deezer.data.Data
import com.fevziomurtekin.deezer.entities.AlbumEntity
import com.fevziomurtekin.deezer.entities.GenreEntity
import timber.log.Timber


fun AlbumEntity?.mapper(): AlbumData? = this?.let { e->
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
}

fun AlbumData?.mapper(): AlbumEntity? = this?.let { d->
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
}

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
} ?: run {
    Data(
        "", "", "", "", "", "", "", ""
    )
}

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
} ?: run {
    GenreEntity(
        genreId = "",
        name= "",
        picture= "",
        pictureBig= "",
        pictureMedium= "",
        pictureSmall= "",
        pictureXl= "",
        type= ""
    )
}


@JvmName("genreMapper")
fun List<GenreEntity?>?.mapper() = this?.let { list ->
    Timber.d("genreMapper : $list")
    val returnList = mutableListOf<Data>()
    list.forEach {
        it.mapper().let { d->
            returnList.add(d)
        }
    }
    return returnList
} ?:run {
    Timber.d("genreMapper : is null")
    mutableListOf<Data>()
}

@JvmName("dataMapper")
fun List<Data>?.mapper() = this?.let { list ->
    val returnList = mutableListOf<GenreEntity>()
    list.forEach {
        it.mapper().let { e->
            returnList.add(e)
        }
    }
    return returnList
} ?: run {
    mutableListOf<GenreEntity>()
}

@JvmName("albumEntityToAlbumData")
fun List<AlbumEntity>.mapper(): List<AlbumData> {
    val returnList = mutableListOf<AlbumData>()
    this.forEach {
        it.mapper()?.let { a->
            returnList.add(a)
        }
    }
    return returnList
}

