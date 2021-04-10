package com.fevziomurtekin.deezer

import android.content.Intent
import android.net.Uri
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.fevziomurtekin.deezer.ui.main.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.CoreMatchers
import org.junit.Rule
import org.junit.Test

/**
 * @author: fevziomurtekin
 * @date: 10/04/2021
 */

@HiltAndroidTest
class DeeplinkTest {

    private val name = "ezhel"
    private val id = 8354140

    private val url = "http://www.example.com/genre/$id/$name"

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var activityTestRule = object : ActivityTestRule<MainActivity>(MainActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return Intent(Intent.ACTION_VIEW, Uri.parse(url))
        }
    }

    @Test
    fun bottomNavView_DeepLink_HandlesIntent_BackGoesToList() {
        // Opening the app with the proper Intent should start it in the profile screen.

        Espresso.pressBack()

        // The list should be shown

        Espresso.pressBack()

        // Home destination should be shown
    }

}
