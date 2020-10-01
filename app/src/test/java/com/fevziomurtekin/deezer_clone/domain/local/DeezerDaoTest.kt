package com.fevziomurtekin.deezer_clone.domain.local

import com.fevziomurtekin.deezer_clone.core.MockUtil
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
        val mockDataList  = MockUtil.genres
        deezerDao.insertGenreList(mockDataList)

        // checking insert procces
        val loadFromDB = deezerDao.getGenreList()
        assertThat(loadFromDB.toString(),`is`(mockDataList.toString()))

        // checking first data.
        val mockData = MockUtil.data
        assertThat(loadFromDB[0].toString(),`is`(mockData.toString()))
    }

    @Test
    fun insertAndLoadSearchQuery() = runBlocking{
        val mockSearchData = MockUtil.recentData
        deezerDao.insertQuery(mockSearchData)

        val mockSearchList = MockUtil.searchList

        // checking insert procces
        val loadFromDB = deezerDao.getQueryList()
        assertThat(loadFromDB.toString(),`is`(mockSearchList.toString()))

        // checking first data.
        assertThat(loadFromDB[0].toString(),`is`(mockSearchData.toString()))
    }




}