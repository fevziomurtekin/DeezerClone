package com.fevziomurtekin.deezer.screen

import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.fevziomurtekin.deezer.R
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.ui.genre.GenreFragment
import com.fevziomurtekin.deezer.ui.main.MainActivity
import com.fevziomurtekin.deezer.utilies.isGone
import com.fevziomurtekin.deezer.utilies.isVisible
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@LargeTest
class GenreFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @Test
    fun `when_loading_data_isGone_shimmer_layout`() {
        ActivityScenario.launch(MainActivity::class.java)
            .moveToState(Lifecycle.State.CREATED)
        launchFragmentInContainer<GenreFragment>().let {
            it.moveToState(Lifecycle.State.CREATED)
            it.onFragment { fragment ->
                fragment.viewModel.fetchResult()
                fragment.viewModel.result.observe(fragment) { result->
                    when(result) {
                        is ApiResult.Success -> {
                            onView(withId(R.id.shimmerLayout)).isGone()
                        }
                        else -> {}
                    }
                }
            }
        }
    }

}