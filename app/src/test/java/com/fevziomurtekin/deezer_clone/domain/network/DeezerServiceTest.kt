package com.fevziomurtekin.deezer_clone.domain.network

import com.fevziomurtekin.deezer_clone.core.ApiAbstract
import com.fevziomurtekin.deezer_clone.di.MainCoroutinesRule
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException
import org.hamcrest.MatcherAssert.assertThat


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
        val responseBody = service.fetchGenreList().await()
        mockWebServer.takeRequest()

        client.fetchGenreList().getCompleted()
        assertThat(responseBody.data.size,`is`(23))
        assertThat(responseBody.data[0].name, `is`("All"))
        assertThat(responseBody.data[0].type, `is`("genre"))
    }

    @Throws(IOException::class)
    @Test
    fun fetchArtistList() = runBlocking {
        enqueueResponse("/artistsResponse.json")
        // give to default genreID
        val responseBody = service.fetchArtistList("0").await()
        mockWebServer.takeRequest()

        client.fetchArtistList("0").getCompleted()
        assertThat(responseBody.artistData[0].id, `is`("8354140"))
        assertThat(responseBody.artistData[0].type, `is`("artist"))
        assertThat(responseBody.artistData[0].name, `is`("ezhel"))

    }

    @Throws(IOException::class)
    @Test
    fun fetchArtistDetails() = runBlocking {
        enqueueResponse("/artistDetailsResponse.json")
        // give to default genreID
        val responseBody = service.fetchArtistDetails("8354140").await()
        mockWebServer.takeRequest()

        client.fetchArtistList("0").getCompleted()
        assertThat(responseBody.id, `is`("8354140"))
        assertThat(responseBody.type, `is`("artist"))
        assertThat(responseBody.name, `is`("ezhel"))
        assertThat(responseBody.radio, `is`(true))

    }

    @Throws(IOException::class)
    @Test
    fun fetchArtistAlbums() = runBlocking {
        enqueueResponse("/artistAlbumsResponse.json")
        // give to default genreID
        val responseBody = service.fetchArtistAlbums("8354140").await()
        mockWebServer.takeRequest()

        client.fetchArtistList("8354140").getCompleted()
        assertThat(responseBody.total, `is`(20))
        assertThat(responseBody.data[0].title, `is`("Müptezhel"))
        assertThat(responseBody.data[0].id,`is`("51174732"))

    }

    @Throws(IOException::class)
    @Test
    fun fetchArtistRelated() = runBlocking {
        enqueueResponse("/artistRelatedResponse.json")
        // give to default genreID
        val responseBody = service.fetchArtistRelated("8354140").await()
        mockWebServer.takeRequest()

        client.fetchArtistList("8354140").getCompleted()
        assertThat(responseBody.data[0].id, `is`("389038"))
        assertThat(responseBody.data[0].type, `is`("artist"))
        assertThat(responseBody.data[0].name, `is`("Murda"))

    }

    @Throws(IOException::class)
    @Test
    fun fetchAlbumDetails() = runBlocking {
        enqueueResponse("/albumDetailsResponse.json")
        // give to default genreID
        val responseBody = service.fetchAlbumDetails("302127").await()
        mockWebServer.takeRequest()

        client.fetchArtistList("302127").getCompleted()
        assertThat(responseBody.data[0].id, `is`("3135553"))
        assertThat(responseBody.data[0].type, `is`("track"))
        assertThat(responseBody.data[0].title, `is`( "One More Time"))

    }

    @Throws(IOException::class)
    @Test
    fun fetchSearchAlbum() = runBlocking {
        enqueueResponse("/searchAlbumResponse.json")
        // give to default genreID
        val responseBody = service.fetchSearchAlbum("ezhel").await()
        mockWebServer.takeRequest()

        client.fetchSearchAlbum("ezhel").getCompleted()
        assertThat(responseBody.data[0].artist.name, `is`("ezhel"))
        assertThat(responseBody.data[0].title, `is`("felaket"))

    }


}


