package com.fevziomurtekin.deezer.ui

import android.content.Intent
import android.net.Uri
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.fevziomurtekin.deezer.R
import com.fevziomurtekin.deezer.ui.main.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.android.synthetic.main.activity_main.*
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author: fevziomurtekin
 * @date: 10/04/2021
 */

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class DeeplinkTest {


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    lateinit var activityRule: ActivityTestRule<MainActivity>


    @Test
    fun openApp_DeepLink_HandlesIntent_BackGoesHomeScreen() {
        //www.example.com/genre/{id}/{name}

        //given
        val defaultTitle = "Deezer Clone"
        val name = "Ezhel"
        val id = "8354140"
        val url = "https://www.deezerclone.com/genre/${id}/${name}"
        activityRule = ActivityTestRule(MainActivity::class.java)

        //when
        activityRule.launchActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))

        //then
        Assert.assertEquals(
            activityRule.activity?.materialToolbar?.title,
            name
        )

        Espresso.pressBack()
        Assert.assertEquals(
            activityRule.activity?.materialToolbar?.title,
            defaultTitle
        )
    }

}
