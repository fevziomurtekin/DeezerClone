package com.fevziomurtekin.deezer

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

/**
 * https://developer.android.com/training/dependency-injection/hilt-testing
 * link was used. In detail at this address.
 */

// A custom runner to set up the instrumented application class for tests.
class CustomTestRunner: AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application = super.newApplication(cl, HiltTestApplication::class.java.name, context)
}
