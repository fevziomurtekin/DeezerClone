package com.fevziomurtekin.deezer.domain.network

import MainCoroutineRule
import com.fevziomurtekin.deezer.core.ApiAbstract
import MockUtil
import com.fevziomurtekin.deezer.data.*
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException


@ExperimentalCoroutinesApi
class DeezerServiceTest : ApiAbstract<DeezerService>() {

    private lateinit var service: DeezerService

    @MockK
    private lateinit var client: DeezerClient

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    // firstly created to service. Then we'll write to test function for each of api call .
    @Before
    fun initService(){
        MockKAnnotations.init(this, relaxed = true)
        service = createService(DeezerService::class.java)
        client = DeezerClient(service)
    }


    @Throws(IOException::class)
    @Test
    fun fetchGenreListTest(): Unit = runBlocking {
        enqueueResponse("/genreResponse.json")
        val response = service.fetchGenreList()
        mockWebServer.takeRequest()

        client.fetchGenreList()

        response.body()?.data?.let { safeList->
            assertThat(safeList.size,`is`(23))
            assertThat(safeList[0].name, `is`("All"))
            assertThat(safeList[0].type, `is`("genre"))
        }
    }

    @Throws(IOException::class)
    @Test
    fun fetchArtistList(): Unit = runBlocking {
        enqueueResponse("/artistsResponse.json")
        // give to default genreID
        val response = service.fetchArtistList(MockUtil.genreID)
        mockWebServer.takeRequest()

        client.fetchArtistList(MockUtil.genreID)

        response?.body()?.artistData?.let { safeList ->
            assertThat(safeList[0].id, `is`("8354140"))
            assertThat(safeList[0].type, `is`("artist"))
            assertThat(safeList[0].name, `is`("ezhel"))
        }

    }

    @Throws(IOException::class)
    @Test
    fun fetchArtistDetails(): Unit = runBlocking {
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
    fun fetchArtistAlbums(): Unit = runBlocking {
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
    fun fetchArtistRelated(): Unit = runBlocking {
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
    fun fetchAlbumDetails(): Unit = runBlocking {
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
    fun fetchSearchAlbum(): Unit = runBlocking {
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


