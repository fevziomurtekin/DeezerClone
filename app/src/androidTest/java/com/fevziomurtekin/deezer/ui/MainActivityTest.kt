package com.fevziomurtekin.deezer.ui

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fevziomurtekin.deezer.R
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.ui.main.MainActivity
import com.fevziomurtekin.deezer.utilies.isVisible
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun test_isGoneSplash() {
        ActivityScenario.launch(MainActivity::class.java).use {
            it.moveToState(Lifecycle.State.CREATED)
            it.onActivity { activity->
                activity.viewModel.genreListLiveData.observe(activity){r->
                   if(r is ApiResult.Success){
                       onView(withId(R.id.cl_splash)).check(matches(not(isDisplayed())))
                   }
                }
            }
        }
    }

    @Test
    fun `when_application_run_the_firstly_is_show_splash_screen`() {
        ActivityScenario.launch(MainActivity::class.java).use {
            it.moveToState(Lifecycle.State.CREATED)
            it.onActivity { activity ->
                onView(withId(R.id.cl_splash)).isVisible()
            }
        }
    }

    @Test
    fun `when_return_the_data_is_gone_application`() {
        ActivityScenario.launch(MainActivity::class.java).use {
            it.moveToState(Lifecycle.State.CREATED)
            it.onActivity { activity->
                onView(withId(R.id.cl_splash)).isVisible()
                activity.viewModel.fetchGenreList()
                activity.viewModel.genreListLiveData.observe(activity) { result->
                    when(result) {
                        is ApiResult.Success -> {
                            onView(withId(R.id.cl_splash)).isVisible()
                        }
                        else -> {
                            onView(withId(R.id.cl_splash)).isVisible()
                        }
                    }
                }

            }
        }
    }
}
