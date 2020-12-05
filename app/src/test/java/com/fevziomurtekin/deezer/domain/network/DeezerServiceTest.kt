package com.fevziomurtekin.deezer.domain.network

import com.fevziomurtekin.deezer.core.ApiAbstract
import com.fevziomurtekin.deezer.core.MockUtil
import com.fevziomurtekin.deezer.data.*
import com.fevziomurtekin.deezer.di.MainCoroutinesRule
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import net.bytebuddy.implementation.bytecode.Throw
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException


@ExperimentalCoroutinesApi
class DeezerServiceTest : ApiAbstract<DeezerService>() {

    private lateinit var service: DeezerService
    private val client: DeezerClient = mockk()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineRule = MainCoroutinesRule()

    // firstly created to service. Then we'll write to test function for each of api call .
    @Before
    fun initService(){
        service = createService(DeezerService::class.java)
    }

    @Throws(IOException::class)
    @Test
    fun fetchGenreListTest() = runBlocking {
        enqueueResponse("/genreResponse.json")
        val responseBody = service.fetchGenreList().body() as? GenreResponse
        mockWebServer.takeRequest()

        client.fetchGenreList()
        responseBody?.data?.let { safeList->
            assertThat(safeList.size,`is`(23))
            assertThat(safeList[0].name, `is`("All"))
            assertThat(safeList[0].type, `is`("genre"))
        } ?: run{
            IOException("unexpected")
        }

    }

    @Throws(IOException::class)
    @Test
    fun fetchArtistList() = runBlocking {
        enqueueResponse("/artistsResponse.json")
        // give to default genreID
        val responseBody = service.fetchArtistList(MockUtil.genreID) as? ArtistsResponse
        mockWebServer.takeRequest()

        client.fetchArtistList(MockUtil.genreID)

        responseBody?.artistData?.let { safeList ->
            assertThat(safeList[0].id, `is`("8354140"))
            assertThat(safeList[0].type, `is`("artist"))
            assertThat(safeList[0].name, `is`("ezhel"))
        } ?: run {
            IOException("unexpected")
        }

    }

    @Throws(IOException::class)
    @Test
    fun fetchArtistDetails() = runBlocking {
        enqueueResponse("/artistDetailsResponse.json")
        // give to default genreID
        val responseBody = service.fetchArtistDetails(MockUtil.artistID) as? ArtistDetailResponse
        mockWebServer.takeRequest()

        client.fetchArtistDetails(MockUtil.artistID)

        responseBody?.let { safeDetail ->
            assertThat(safeDetail.id, `is`("8354140"))
            assertThat(safeDetail.type, `is`("artist"))
            assertThat(safeDetail.name, `is`("ezhel"))
            assertThat(safeDetail.radio, `is`(true))
        } ?: run {
            IOException("unexpected error")
        }


    }

    @Throws(IOException::class)
    @Test
    fun fetchArtistAlbums() = runBlocking {
        enqueueResponse("/artistAlbumsResponse.json")
        // give to default genreID
        val responseBody = service.fetchArtistAlbums(MockUtil.artistID) as? ArtistAlbumResponse
        mockWebServer.takeRequest()

        client.fetchArtistList(MockUtil.artistID)

        responseBody?.data?.let { safeList->
            assertThat(safeList.size, `is`(20))
            assertThat(safeList[0].title, `is`("Müptezhel"))
            assertThat(safeList[0].id, `is`("51174732"))
        } ?: run{
            IOException("unexpected")
        }

    }

    @Throws(IOException::class)
    @Test
    fun fetchArtistRelated() = runBlocking {
        enqueueResponse("/artistRelatedResponse.json")
        // give to default genreID
        val responseBody = service.fetchArtistRelated(MockUtil.artistID) as? ArtistRelatedResponse
        mockWebServer.takeRequest()

        client.fetchArtistList(MockUtil.albumID)

        responseBody?.data?.let { safeList ->
            assertThat(safeList[0].id, `is`("389038"))
            assertThat(safeList[0].type, `is`("artist"))
            assertThat(safeList[0].name, `is`("Murda"))
        }

    }

    @Throws(IOException::class)
    @Test
    fun fetchAlbumDetails() = runBlocking {
        enqueueResponse("/albumDetailsResponse.json")
        // give to default genreID
        val responseBody = service.fetchAlbumDetails(MockUtil.albumID) as? AlbumDetailsResponse
        mockWebServer.takeRequest()

        client.fetchArtistList(MockUtil.albumID)

        responseBody?.data?.let { safeList->
            assertThat(safeList[0].id, `is`("3135553"))
            assertThat(safeList[0].type, `is`("track"))
            assertThat(safeList[0].title, `is`( "One More Time"))
        } ?: run{
            IOException("unexpected")
        }
    }

    @Throws(IOException::class)
    @Test
    fun fetchSearchAlbum() = runBlocking {
        enqueueResponse("/searchAlbumResponse.json")
        // give to default genreID
        val responseBody = service.fetchSearchAlbum(MockUtil.query) as? SearchResponse
        mockWebServer.takeRequest()

        client.fetchSearchAlbum(MockUtil.query)

        responseBody?.data?.let { safeList->
            assertThat(safeList[0].artist.name, `is`("ezhel"))
            assertThat(safeList[0].title, `is`("felaket"))
        }
    }


}


