package com.fevziomurtekin.deezer.ui

import android.content.Intent
import android.net.Uri
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.fevziomurtekin.deezer.R
import com.fevziomurtekin.deezer.ui.main.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
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
    fun openApp_withGenereDetails_DeepLink_HandlesIntent_BackGoesHomeScreen() {
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
        onView(withId(R.id.materialToolbar))
            .check(matches(hasDescendant(withText(name))))
        Espresso.pressBack()
        onView(withId(R.id.materialToolbar))
            .check(matches(hasDescendant(withText(defaultTitle))))

    }

    @Test
    fun openApp_withArtistDetails_DeepLink_HandlesIntent_BackGoesGenreScreen() {
        //www.example.com/genre/{id}/{name}

        //given
        val name = "Ezhel"
        val id = "8354140"
        val url = "https://www.deezerclone.com/artist/${id}/${name}"
        activityRule = ActivityTestRule(MainActivity::class.java)

        //when
        activityRule.launchActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))

        //then
        onView(withId(R.id.materialToolbar))
            .check(matches(hasDescendant(withText(name))))
        Espresso.pressBack()
        onView(withId(R.id.materialToolbar))
            .check(matches(hasDescendant(withText(name))))

    }

    fun openApp_withAlbumDetails_DeepLink_HandlesIntent_BackGoesGenreScreen() {
        //www.example.com/genre/{id}/{name}

        //given
        val defaultTitle = "Deezer Clone"
        val name = "Ezhel"
        val id = "8354140"
        val url = "https://www.deezerclone.com/artist/${id}/${name}"
        activityRule = ActivityTestRule(MainActivity::class.java)

        //when
        activityRule.launchActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))

        //then
        onView(withId(R.id.materialToolbar))
            .check(matches(hasDescendant(withText(name))))
        Espresso.pressBack()
        onView(withId(R.id.materialToolbar))
            .check(matches(hasDescendant(withText(name))))

    }

}
