package com.fevziomurtekin.deezer.ui.genre

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
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.fevziomurtekin.deezer.R
import MockUtil
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.data.Data
import com.fevziomurtekin.deezer.domain.local.DeezerDao
import com.fevziomurtekin.deezer.domain.network.DeezerClient
import com.fevziomurtekin.deezer.domain.network.DeezerService
import com.fevziomurtekin.deezer.launchFragmentInHiltContainer
import com.fevziomurtekin.deezer.ui.main.MainRepository
import com.fevziomurtekin.deezer.utilies.MainCoroutinesRule
import com.fevziomurtekin.deezer.utilies.isGone
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * @author: fevziomurtekin
 * @date: 12/04/2021
 */
@ExperimentalCoroutinesApi
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@MediumTest
class GenreFragmentTest {

  @get:Rule
  var hiltRule = HiltAndroidRule(this)

  lateinit var controller: NavController

  var fragment: GenreFragment? = null

  @MockK
  private lateinit var repository: MainRepository

  private lateinit var viewModel: GenreViewModel

  @MockK
  lateinit var deezerService: DeezerService

  lateinit var deezerClient: DeezerClient

  @MockK
  lateinit var deezerDao: DeezerDao

  @ExperimentalCoroutinesApi
  @get:Rule
  var coroutineRule = MainCoroutinesRule()

  @Before
  fun setUp() {
    MockKAnnotations.init(this, relaxed = true)
    controller = TestNavHostController(
        ApplicationProvider.getApplicationContext())
    controller.setGraph(R.navigation.nav_graph)

    launchFragmentInHiltContainer<GenreFragment> {
      Navigation.setViewNavController(requireView(), controller)
      fragment = this as? GenreFragment
    }
    deezerClient = DeezerClient(deezerService)
    viewModel = GenreViewModel(repository)
  }

  @Test
  fun when_loading_data_isGone_shimmer_layout() {
    fragment?.let { safeFragment ->

      //given
      val mockListEntity = MockUtil.genreEntityList

      coEvery { deezerDao.getGenreList() } returns mockListEntity

      coroutineRule.launch {
        val observer : Observer<ApiResult<List<Data>?>> = mockk()
        val fetchedData : LiveData<ApiResult<List<Data>?>> =
            repository.fetchGenreList().asLiveData()
        fetchedData.observeForever(observer)

        viewModel.fetchResult()
      }
      onView(withId(R.id.shimmerLayout)).isGone()
    }
  }
  @Test
  fun launchGenreFragment_clickedItemScenario(){
    fragment?.let { safeFragment ->

      //given
      val mockListEntity = MockUtil.genreEntityList

      coEvery { deezerDao.getGenreList() } returns mockListEntity

      coroutineRule.launch {
        val observer : Observer<ApiResult<List<Data>?>> = mockk()
        val fetchedData : LiveData<ApiResult<List<Data>?>> =
            repository.fetchGenreList().asLiveData()
        fetchedData.observeForever(observer)

        safeFragment.viewModel.fetchResult()
      }
      openGenreScreen(2)

      onView(withId(R.id.materialToolbar))
          .check(ViewAssertions.matches(hasDescendant(withText("Rock"))))

    }
  }


  private fun openGenreScreen(position: Int) {
    onView(withId(R.id.recycler_genre))
        .perform(RecyclerViewActions
            .actionOnItemAtPosition<RecyclerView.ViewHolder>
            (position, click()))
  }
}
