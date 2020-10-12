package com.fevziomurtekin.deezer.di

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.rules.TestRule
import kotlinx.coroutines.test.*
import org.junit.runner.Description
import org.junit.runners.model.Statement

@ExperimentalCoroutinesApi
class MainCoroutinesRule : TestRule, TestCoroutineScope by TestCoroutineScope() {

  private val testCoroutinesDispatcher = TestCoroutineDispatcher()

  override fun apply(base: Statement?, description: Description?) = object : Statement() {
    override fun evaluate() {
      Dispatchers.setMain(testCoroutinesDispatcher)
      cleanupTestCoroutines()
      Dispatchers.resetMain()
    }
  }
}

