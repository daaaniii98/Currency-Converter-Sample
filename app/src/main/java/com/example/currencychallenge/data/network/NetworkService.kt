package com.example.currencychallenge.data.network

import com.example.currencychallenge.BuildConfig
import com.example.currencychallenge.data.network.model.response.CurrenciesModel
import com.example.currencychallenge.data.network.model.response.CurrencyRatesModel
import retrofit2.http.*

interface NetworkService {

    @GET("api/currencies.json")
    suspend fun getAvailableCurrencies(
        @Query("show_inactive") showInactive:Boolean = false,
        @Query("app_id") appId:String = BuildConfig.APP_ID,
    ): CurrenciesModel

    @GET("api/latest.json")
    suspend fun getCurrencyExchangeRates(
        @Query("app_id") appId:String = BuildConfig.APP_ID,
    ): CurrencyRatesModel

}