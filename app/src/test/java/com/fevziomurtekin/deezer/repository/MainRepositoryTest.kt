package com.fevziomurtekin.deezer.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.fevziomurtekin.deezer.core.MockUtil
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.data.albumdetails.AlbumData
import com.fevziomurtekin.deezer.data.albumdetails.AlbumDetailsResponse
import com.fevziomurtekin.deezer.data.artist.ArtistData
import com.fevziomurtekin.deezer.data.artist.ArtistsResponse
import com.fevziomurtekin.deezer.data.artistdetails.*
import com.fevziomurtekin.deezer.data.genre.Data
import com.fevziomurtekin.deezer.data.genre.GenreResponse
import com.fevziomurtekin.deezer.data.search.SearchData
import com.fevziomurtekin.deezer.data.search.SearchResponse
import com.fevziomurtekin.deezer.di.MainCoroutinesRule
import com.fevziomurtekin.deezer.domain.local.DeezerDao
import com.fevziomurtekin.deezer.domain.network.DeezerClient
import com.fevziomurtekin.deezer.domain.network.DeezerService
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.CompletableDeferred
import org.junit.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
class MainRepositoryTest {

    private lateinit var repository: DeezerRepository
    private lateinit var client: DeezerClient
    private val service: DeezerService = mock()
    private val deezerDao: DeezerDao = mock()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        client = DeezerClient(service)
        repository = DeezerRepository(client, deezerDao)
    }

    @ExperimentalTime
    @Test
    fun fetchGenreListFromNetworkTest() = runBlocking {
        val mockData = GenreResponse(MockUtil.genres)
        val deferredData = CompletableDeferred(mockData)
        whenever(deezerDao.getGenreList()).thenReturn(emptyList())
        whenever(service.fetchGenreList()).thenReturn(deferredData)

        /** core testing */
       /* val result = repository.fetchGenreList().asLiveData().value
        Assert.assertEquals(result, Result.Succes(MockUtil.genres))*/

        /** add to turbine library.*/
        repository.fetchGenreList().test {
            val result = expectItem()
            assertEquals(result, ApiResult.Succes(MockUtil.genres))
            assertEquals((result as ApiResult.Succes<List<Data>>).data[0].name , "All")
            assertEquals(result.data[0].id , "0")
            assertEquals(result.data[1].name , "Pop")
            assertEquals(result.data[1].id , "132")
            expectComplete()
        }

        verify(deezerDao, atLeastOnce()).getGenreList()
        verify(service, atLeastOnce()).fetchGenreList().await()
        verify(deezerDao, atLeastOnce()).insertGenreList(MockUtil.genres)

    }

    @ExperimentalTime
    @Test
    fun fetchArtistListFromNetworkTest() = runBlocking {
        val mockData = ArtistsResponse(listOf(MockUtil.artist))
        val deferredData = CompletableDeferred(mockData)
        whenever(service.fetchArtistList(MockUtil.genreID)).thenReturn(deferredData)

        repository.fetchArtistList(MockUtil.genreID).test {
            val result = expectItem()
            assertEquals(result, ApiResult.Succes(listOf(MockUtil.artist)))
            assertEquals((result as ApiResult.Succes<List<ArtistData>>).data[0].name , "Ezhel")
            assertEquals(result.data[0].id , "8354140")
            assertEquals(result.data[1].name , "Sagopa Kajmer")
            assertEquals(result.data[1].id , "12934")
            expectComplete()
        }

        verify(service, atLeastOnce()).fetchArtistList(MockUtil.genreID).await()
        verifyNoMoreInteractions(service)
    }



    @ExperimentalTime
    @Test
    fun fetchArtistDetailsFromNetworkTest() = runBlocking {
        val mockData = MockUtil.artistDetails
        val deferredData = CompletableDeferred(mockData)
        whenever(service.fetchArtistDetails(MockUtil.artistID)).thenReturn(deferredData)

        repository.fetchArtistDetails(MockUtil.artistID).test {
            val result = expectItem()
            assertEquals(result, ApiResult.Succes(mockData))
            assertEquals((result as ApiResult.Succes<ArtistDetailResponse>).data.name , "Ezhel")
            assertEquals(result.data.id , "8354140")
            expectComplete()
        }

        verify(service, atLeastOnce()).fetchArtistList(MockUtil.artistID).await()
        verifyNoMoreInteractions(service)
    }

    @ExperimentalTime
    @Test
    fun fetchArtistAlbumsFromNetworkTest() = runBlocking {
        val mockData = ArtistAlbumResponse(data= listOf(MockUtil.artistAlbum),next= "",total=20)
        val deferredData = CompletableDeferred(mockData)
        whenever(service.fetchArtistAlbums(MockUtil.artistID)).thenReturn(deferredData)

        repository.fetchArtistAlbums(MockUtil.artistID).test {
            val result = expectItem()
            assertEquals(result, ApiResult.Succes(listOf(MockUtil.artistAlbum)))
            assertEquals((result as ApiResult.Succes<List<ArtistAlbumData>>).data[0].title , "Müptezhel")
            assertEquals(result.data[0].id , "51174732")
            assertEquals(result.data[1].title , "Made In Turkey")
            assertEquals(result.data[1].id , "155597932")
            expectComplete()
        }

        verify(service, atLeastOnce()).fetchArtistAlbums(MockUtil.artistID).await()
        verifyNoMoreInteractions(service)
    }

    @ExperimentalTime
    @Test
    fun fetchArtistRelatedFromNetworkTest() = runBlocking {
        val mockData = ArtistRelatedResponse(data= listOf(MockUtil.artistRelatedData),total=20)
        val deferredData = CompletableDeferred(mockData)
        whenever(service.fetchArtistRelated(MockUtil.artistID)).thenReturn(deferredData)

        repository.fetchArtistRelated(MockUtil.artistID).test {
            val result = expectItem()
            assertEquals(result, ApiResult.Succes(listOf(MockUtil.artistRelatedData)))
            assertEquals((result as ApiResult.Succes<List<ArtistRelatedData>>).data[0].name , "Murda")
            assertEquals(result.data[0].id , "389038")
            assertEquals(result.data[1].name , "Reynmen")
            assertEquals(result.data[1].id , "13136341")
            expectComplete()
        }

        verify(service, atLeastOnce()).fetchArtistRelated(MockUtil.artistID).await()
        verifyNoMoreInteractions(service)
    }


    @ExperimentalTime
    @Test
    fun fetchAlbumTracksFromNetworkTest() = runBlocking {
        val mockData = AlbumDetailsResponse(data= listOf(MockUtil.album),total=12)
        val deferredData = CompletableDeferred(mockData)
        whenever(service.fetchAlbumDetails(MockUtil.albumID)).thenReturn(deferredData)

        repository.fetchAlbumTracks(MockUtil.albumID).test {
            val result = expectItem()
            assertEquals(result, ApiResult.Succes(listOf(MockUtil.album)))
            assertEquals((result as ApiResult.Succes<List<AlbumData>>).data[0].title , "Alo")
            assertEquals(result.data[0].id , "425605922")
            assertEquals(result.data[1].title , "Geceler")
            assertEquals(result.data[1].id , "425605932")
            expectComplete()
        }

        verify(service, atLeastOnce()).fetchAlbumDetails(MockUtil.albumID).await()
        verifyNoMoreInteractions(service)
    }

    @ExperimentalTime
    @Test
    fun fetchRecentSearchFromDaoTest() = runBlocking {
        val mockData = listOf(MockUtil.recentData)
        whenever(deezerDao.getQueryList()).thenReturn(mockData)

        repository.fetchRecentSearch().test {
            val result = expectItem()
            assertEquals(result, mockData)
            assertEquals(result[0].q , "Ezhel")
            assertEquals(result[0].id , "1")
            expectComplete()
        }

        verify(deezerDao, atLeastOnce()).getQueryList()
        verifyNoMoreInteractions(deezerDao)
    }


    @ExperimentalTime
    @Test
    fun insertSearchFromDaoTest() = runBlocking {
        val mockData = MockUtil.recentData
        whenever(deezerDao.getQueryList()).thenReturn(emptyList())

        /*"1","ezhel",12345.toLong()*/
        repository.insertSearch(mockData)
        repository.fetchRecentSearch().test {
            val result = expectItem()
            assertEquals(result, mockData)
            assertEquals(result[0].q , "Ezhel")
            assertEquals(result[0].id , "1")
            assertEquals(result[0].time,12345.toLong())
            expectComplete()
        }

        verify(deezerDao, atLeastOnce()).insertQuery(MockUtil.recentData)
    }

    @ExperimentalTime
    @Test
    fun fetchSearchFromNetworkTest() = runBlocking {
        val mockData = SearchResponse(data= listOf(MockUtil.searchData),next="",total=12)
        val deferredData = CompletableDeferred(mockData)
        whenever(service.fetchSearchAlbum(MockUtil.query)).thenReturn(deferredData)

        repository.fetchSearch(MockUtil.query).test {
            val result = expectItem()
            assertEquals(result, ApiResult.Succes(listOf(MockUtil.searchData)))
            assertEquals((result as ApiResult.Succes<List<SearchData>>).data[0].title , "İmkansızım")
            assertEquals(result.data[0].id , "51434782")
            assertEquals(result.data[0].record_type , "single")
            assertEquals(result.data[0].genre_id , "116")
            expectComplete()
        }

        verify(service, atLeastOnce()).fetchSearchAlbum(MockUtil.query).await()
        verifyNoMoreInteractions(service)
    }

    @ExperimentalTime
    @Test
    fun fetchFavoriteFromDaoTest() = runBlocking{
        val mockData = listOf(MockUtil.album)
        whenever(deezerDao.getFavorites()).thenReturn(mockData)

        repository.fetchFavorites().test {
            val result = expectItem()
            assertEquals(result,mockData)
            assertEquals(result[0].title , "Alo")
            assertEquals(result[0].id , "425605922")
            assertEquals(result[1].title , "Geceler")
            assertEquals(result[1].id , "425605932")
        }

        verify(deezerDao, atLeastOnce()).getFavorites()
        verifyNoMoreInteractions(deezerDao)
    }

    @ExperimentalTime
    @Test
    fun insertFavoriteTrackFromDaoTest()= runBlocking {
        val mockData = MockUtil.album
        whenever(deezerDao.getFavorites()).thenReturn(emptyList())

        repository.insertFavoritesData(mockData)
        repository.fetchFavorites().test {
            val result = expectItem()
            assertEquals(result,mockData)
            assertEquals(result[0].title , "Alo")
            assertEquals(result[0].id , "425605922")
            assertEquals(result[1].title , "Geceler")
            assertEquals(result[1].id , "425605932")
        }
        verify(deezerDao, atLeastOnce()).insertTrack(MockUtil.album)
    }



}