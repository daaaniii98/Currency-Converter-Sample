package com.example.currencychallenge.core.workers

import android.content.Context
import android.util.Log
import androidx.work.*
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

const val REFRESH_DATA_WORK = "refresh_data_work"
const val REFRESH_INTERVAL = 30L
private const val TAG = "MyWorkManager"

interface MyWorkManager {
    fun cancelWork()
    fun enqueuePeriodicWork()
}

class MyWorkManagerImp @Inject constructor(@ApplicationContext context: Context) : MyWorkManager {
    private val mWorkManager:WorkManager = WorkManager.getInstance(context)
    override fun cancelWork() {
        mWorkManager.cancelUniqueWork(REFRESH_DATA_WORK)
    }

    override fun enqueuePeriodicWork() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val periodicSyncDataWork =
            PeriodicWorkRequest.Builder(RefreshDataWorker::class.java, REFRESH_INTERVAL , TimeUnit.MINUTES)
                .addTag(TAG)
                .setConstraints(constraints)
                .setBackoffCriteria(
                    BackoffPolicy.LINEAR,
                    PeriodicWorkRequest.MIN_BACKOFF_MILLIS,
                    TimeUnit.MILLISECONDS
                )
                .build()
        mWorkManager.enqueueUniquePeriodicWork(
            REFRESH_DATA_WORK,
            ExistingPeriodicWorkPolicy.KEEP,
            periodicSyncDataWork
        )
    }
}


