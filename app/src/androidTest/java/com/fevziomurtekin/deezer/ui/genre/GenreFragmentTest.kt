package com.fevziomurtekin.deezer.ui.genre

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.fevziomurtekin.deezer.R
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.launchFragmentInHiltContainer
import com.fevziomurtekin.deezer.utilies.isGone
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.CoreMatchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * @author: fevziomurtekin
 * @date: 12/04/2021
 */
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@MediumTest
class GenreFragmentTest {

  @get:Rule
  var hiltRule = HiltAndroidRule(this)

  lateinit var controller: NavController

  var fragment: GenreFragment? = null


  @Before
  fun setUp() {
    controller = TestNavHostController(
        ApplicationProvider.getApplicationContext())
    controller.setGraph(R.navigation.nav_graph)
    launchFragmentInHiltContainer<GenreFragment> {
      Navigation.setViewNavController(requireView(), controller)
      fragment = this as? GenreFragment
    }
  }

  @Test
  fun `when_loading_data_isGone_shimmer_layout`() {
    fragment?.let { safeFragment->
      safeFragment.viewModel.fetchResult()
      safeFragment.viewModel.result.observe(safeFragment) { result->
        when(result) {
          is ApiResult.Success -> {
            onView(withId(R.id.shimmerLayout)).isGone()
          }
          else -> {}
        }
      }
    }
  }

  @Test
  fun launchGenreFragment_clickedItemScenario(){
    //given
    val genre = "ROCK"
    val toolbarTitle = "Rock"

    //when
    fragment?.viewModel?.fetchResult()

    // rock is 3 position on genre recyclerview.
    observeOnSuccess(fragment) {
      openGenreScreen(3)
      Truth.assertThat(controller.currentDestination?.id)
          .isEqualTo(R.id.genre_list)
    }
  }


  private fun openGenreScreen(position: Int) {
    onView(withId(R.id.recycler_genre))
        .perform(RecyclerViewActions
            .actionOnItemAtPosition<RecyclerView.ViewHolder>
            (position, click()))
  }

  private fun observeOnSuccess(
      fragment: GenreFragment?,
      onSuccess: () -> Unit
  ) {
    fragment?.viewModel?.result?.observe(fragment) { result->
      when(result) {
        is ApiResult.Success -> {
          onSuccess.invoke()
        }
        else -> Unit
      }
    }
  }

}