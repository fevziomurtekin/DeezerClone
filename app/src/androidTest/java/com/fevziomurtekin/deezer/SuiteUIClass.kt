package com.fevziomurtekin.deezer

import com.fevziomurtekin.deezer.ui.GenreFragmentTest
import com.fevziomurtekin.deezer.ui.MainActivityTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

@ExperimentalCoroutinesApi
@RunWith(Suite::class)
@Suite.SuiteClasses(
    MainActivityTest::class,
    GenreFragmentTest::class
)
class SuiteUIClass