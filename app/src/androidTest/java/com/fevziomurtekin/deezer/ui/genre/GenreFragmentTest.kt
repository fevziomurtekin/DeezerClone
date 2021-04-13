package com.fevziomurtekin.deezer.ui.genre

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.fevziomurtekin.deezer.R
import com.fevziomurtekin.deezer.launchFragmentInHiltContainer
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
  var instantTaskExecutorRule = InstantTaskExecutorRule()

  @get:Rule
  var hiltRule = HiltAndroidRule(this)

  lateinit var controller: NavController


  @Before
  fun setUp() {
    controller = TestNavHostController(
        ApplicationProvider.getApplicationContext())
    controller.setGraph(R.navigation.nav_graph)
    launchFragmentInHiltContainer<GenreFragment> {
      Navigation.setViewNavController(requireView(), controller)
    }
  }

  @Test
  fun launchGenreFragment_clickedItemScenario(){
    //given
    val genre = "ROCK"
    val toolbarTitle = "Rock"


    // rock is 3 position on genre recyclerview.
    openGenreScreen(3)
    assertMaterialToolbarTitle(toolbarTitle)

  }

  private fun assertMaterialToolbarTitle(toolbarTitle: String) {
    Espresso.onView(withId(R.id.materialToolbar))
        .check(ViewAssertions.matches(
            ViewMatchers.hasDescendant(ViewMatchers.withText(toolbarTitle))
        ))
  }

  private fun openGenreScreen(position: Int) {
    Espresso.onView(allOf(withId(R.id.recycler_genre), isDisplayed()))
        .perform(RecyclerViewActions
            .actionOnItemAtPosition<RecyclerView.ViewHolder>(
                position, click()
            ))
  }


}