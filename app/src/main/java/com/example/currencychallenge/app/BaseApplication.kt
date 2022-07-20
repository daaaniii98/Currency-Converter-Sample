package com.example.currencychallenge.app

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.example.currencychallenge.core.workers.MyWorkManager
import com.example.currencychallenge.core.workers.MyWorkManagerImp
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class BaseApplication : Application(), Configuration.Provider {
    @Inject
    lateinit var workerFactory: HiltWorkerFactory
    @Inject
    lateinit var myWorkManager: MyWorkManager

    override fun onCreate() {
        super.onCreate()
        myWorkManager.enqueuePeriodicWork()
    }

    override fun getWorkManagerConfiguration(): Configuration =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .setMinimumLoggingLevel(android.util.Log.INFO)
            .build()
}