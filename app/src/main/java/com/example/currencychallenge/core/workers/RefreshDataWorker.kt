package com.example.currencychallenge.core.workers

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.currencychallenge.data.repo.AppRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking

@HiltWorker
class RefreshDataWorker @AssistedInject constructor(
    @Assisted context: Context, @Assisted workerParameters: WorkerParameters,
    private val appRepository: AppRepository
) :
    Worker(context, workerParameters) {
    override fun doWork(): Result {
        return runBlocking {
            combine(appRepository.getCurrencies(true),appRepository.getCurrencyExchangeRates(true)) { r1, r2 ->
                if(r1.isSuccess && r2.isSuccess){
                    Result.success()
                }else{
                    Result.failure()
                }
            }.first()
        }
    }
}
