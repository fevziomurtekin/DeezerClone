package com.fevziomurtekin.deezer.ui.favorites

import MainCoroutineRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.fevziomurtekin.deezer.R
import MockUtil.albumEntity
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.domain.local.DeezerDao
import com.fevziomurtekin.deezer.domain.network.DeezerClient
import com.fevziomurtekin.deezer.domain.network.DeezerService
import com.fevziomurtekin.deezer.entities.AlbumEntity
import com.fevziomurtekin.deezer.launchFragmentInHiltContainer
import com.fevziomurtekin.deezer.utilies.isVisible
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author: fevziomurtekin
 * @date: 17/04/2021
 */
@ExperimentalCoroutinesApi
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@MediumTest
class FavoritesFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    lateinit var controller: NavController

    var fragment: FavoritesFragment? = null

    @MockK
    private lateinit var repository: FavoritesRepository

    private lateinit var viewModel: FavoritesViewModel

    @MockK
    lateinit var deezerService: DeezerService

    lateinit var deezerClient: DeezerClient

    @MockK
    lateinit var deezerDao: DeezerDao

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        controller = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        controller.setGraph(R.navigation.nav_graph)

        launchFragmentInHiltContainer<FavoritesFragment> {
            Navigation.setViewNavController(requireView(), controller)
            fragment = this as? FavoritesFragment
        }
        deezerClient = DeezerClient(deezerService)
        viewModel = FavoritesViewModel(repository)
    }

    @Test
    fun clicked_item_after_then_load_favorites_data_show_media_player() {
        fragment?.let { favoritesFragment ->
            //given
            val res = listOf(albumEntity, albumEntity, albumEntity)

            coEvery { deezerDao.getFavorites() } returns res

            //when
            coroutineRule.launch {
                val observer : Observer<ApiResult<List<AlbumEntity>?>> = mockk()
                val favoritesData : LiveData<ApiResult<List<AlbumEntity>?>> =
                    repository.fetchFavorites().asLiveData()
                favoritesData.observeForever(observer)

                viewModel.fetchFavorites()
            }
            clickedFavoritesItem()

            onView(withId(R.id.cl_media_player)).isVisible()
        }
    }

    private fun clickedFavoritesItem() {
        onView(withId(R.id.recycler_favorites))
            .perform(RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(
                2, click()))
    }
}
