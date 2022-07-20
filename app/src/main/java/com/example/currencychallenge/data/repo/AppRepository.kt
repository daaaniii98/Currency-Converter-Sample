package com.example.currencychallenge.data.repo

import android.util.Log
import com.example.currencychallenge.core.exception.NetworkException
import com.example.currencychallenge.core.exception.ServerException
import com.example.currencychallenge.data.local.dao.CurrencyDao
import com.example.currencychallenge.data.local.dao.CurrencyRatesDao
import com.example.currencychallenge.data.network.NetworkService
import com.example.currencychallenge.data.network.model.response.CurrenciesModel
import com.example.currencychallenge.data.network.model.response.CurrencyRatesModel
import com.example.currencychallenge.utils.NetworkHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

interface AppRepository {
    fun getCurrencies(forceRefreshData:Boolean = false): Flow<Result<CurrenciesModel>>
    fun getCurrencyExchangeRates(forceRefreshData:Boolean = false): Flow<Result<CurrencyRatesModel.Rates>>
}

class AppRepositoryImpl @Inject constructor(
    private val remoteApi: NetworkService,
    private val currencyDao: CurrencyDao,
    private val currencyRateDao: CurrencyRatesDao,
    private val networkHandler: NetworkHandler,
) : AppRepository {

    override fun getCurrencies(forceRefreshData:Boolean): Flow<Result<CurrenciesModel>> = currencyDao.getCurrenciesFlow().transform {
        if (it == null || forceRefreshData) {
            if (networkHandler.isNetworkAvailable()) {
                val resp = remoteApi.getAvailableCurrencies()
                currencyDao.deleteAndInsertCurrencies(resp)
                emit(Result.success(currencyDao.getCurrencies()!!))
            } else {
                emit(Result.failure<CurrenciesModel>(NetworkException))
            }
        } else {
            emit(Result.success(it))
        }
    }.catch {
        currencyDao.getCurrencies()?.let {
            emit(Result.success(it))
        } ?: emit(Result.failure(ServerException))
    }.flowOn(Dispatchers.IO)

    override fun getCurrencyExchangeRates(forceRefreshData: Boolean): Flow<Result<CurrencyRatesModel.Rates>> = currencyRateDao.getCurrencyRatesFlow().transform {
            if (it == null|| forceRefreshData) {
                if (networkHandler.isNetworkAvailable()) {
                    val resp = remoteApi.getCurrencyExchangeRates()
                    currencyRateDao.deleteAndInsertCurrencyRates(resp.rates)
                    emit(Result.success(resp.rates))
                } else {
                    emit(Result.failure<CurrencyRatesModel.Rates>(NetworkException))
                }
            } else {
                emit(Result.success(it))
            }
        }.catch {
            emit(Result.failure(ServerException))
        }.flowOn(Dispatchers.IO)
}

private const val TAG = "AppRepository"