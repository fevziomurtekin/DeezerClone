package com.fevziomurtekin.deezer.domain.local

import android.os.Build
import com.fevziomurtekin.deezer.core.MockUtil
import com.fevziomurtekin.deezer.core.mapper.mapper
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1], manifest = Config.NONE)
class DeezerDaoTest: LocalDatabase(){

    private lateinit var deezerDao: DeezerDao

    @Before
    fun init(){
        deezerDao = db.deezerDao()
    }

    @Test
    fun insertAndLoadGenreList() = runBlocking {
        val mockDataList  = MockUtil.genreEntityList
        deezerDao.insertGenreList(mockDataList)

        // checking insert process
        val loadFromDB = deezerDao.getGenreList() ?: emptyList()
        assertThat(loadFromDB.toString(),`is`(mockDataList.toString()))

        // checking first data.
        val mockData = MockUtil.data
        assertThat(loadFromDB[0].mapper().toString(),`is`(mockData.toString()))
    }

    @Test
    fun insertAndLoadSearchQuery() = runBlocking{
        val mockSearchData = MockUtil.recentData
        deezerDao.insertQuery(mockSearchData)

        val mockSearchList = MockUtil.searchList

        // checking insert process
        val loadFromDB = deezerDao.getQueryList()
        assertThat(Gson().toJson(loadFromDB),`is`(Gson().toJson(mockSearchList)))

        // checking first data.
        assertThat(loadFromDB[0].q,`is`(mockSearchData.q))
    }

    @Test
    fun insertAndLoadFavorites() = runBlocking {
        val mockFavorite = MockUtil.albumEntity
        deezerDao.insertTrack(mockFavorite)

        val mockFavorites = listOf(mockFavorite)

        val loadFromDB = deezerDao.getFavorites() ?: emptyList()
        assertThat(Gson().toJson(loadFromDB),`is`(Gson().toJson(mockFavorites)))

        assertThat(loadFromDB[0].albumId,`is`(mockFavorite.albumId))

    }


}