package com.fevziomurtekin.deezer.ui

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.fevziomurtekin.deezer.ui.main.MainActivity
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.fevziomurtekin.deezer.AppTestRunner
import com.fevziomurtekin.deezer.R
import com.fevziomurtekin.deezer.core.Result
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.runners.JUnit4

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
                   if(r is Result.Succes){
                       onView(withId(R.id.cl_splash)).check(matches(not(isDisplayed())))
                   }
                }
            }
        }
    }


}