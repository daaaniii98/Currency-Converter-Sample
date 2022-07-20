package com.example.currencychallenge.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class CoroutineTestRule constructor(
    val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher(),
) : TestWatcher() {

    val defaultDispatcher get() = testDispatcher

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}

@ExperimentalCoroutinesApi
fun CoroutineTestRule.runBlockingTest(block: suspend () -> Unit): Unit =
    this.testDispatcher.runBlockingTest {
        block()
    }
