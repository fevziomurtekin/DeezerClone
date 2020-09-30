package com.fevziomurtekin.deezer_clone.domain.local

import com.fevziomurtekin.deezer_clone.data.genre.Data
import com.fevziomurtekin.deezer_clone.data.search.SearchQuery
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
class DeezerDaoTest:LocalDatabase(){

    private lateinit var deezerDao: DeezerDao

    @Before
    fun init(){
        deezerDao = db.deezerDao()
    }

    @Test
    fun insertAndLoadGenreList() = runBlocking {
        val mockDataList  = genres
        deezerDao.insertGenreList(mockDataList)

        // checking insert procces
        val loadFromDB = deezerDao.getGenreList()
        assertThat(loadFromDB.toString(),`is`(mockDataList.toString()))

        // checking first data.
        val mockData = data
        assertThat(loadFromDB[0].toString(),`is`(mockData.toString()))
    }

    @Test
    fun insertAndLoadSearchQuery() = runBlocking{
        val mockSearchData = searchData
        deezerDao.insertQuery(mockSearchData)

        val mockSearchList = searchList

        // checking insert procces
        val loadFromDB = deezerDao.getQueryList()
        assertThat(loadFromDB.toString(),`is`(mockSearchList.toString()))

        // checking first data.
        assertThat(loadFromDB[0].toString(),`is`(mockSearchData.toString()))
    }


    companion object{
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

        val searchData = SearchQuery("1","ezhel",12345.toLong())

        val searchList = listOf(searchData)
    }

}