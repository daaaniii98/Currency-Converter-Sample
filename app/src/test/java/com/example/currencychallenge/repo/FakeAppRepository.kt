package com.example.currencychallenge.repo

import com.example.currencychallenge.data.network.model.response.CurrenciesModel
import com.example.currencychallenge.data.network.model.response.CurrencyRatesModel
import com.example.currencychallenge.data.repo.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeAppRepository : AppRepository {
    override fun getCurrencies(forceRefreshData: Boolean): Flow<Result<CurrenciesModel>> = flowOf(
        Result.success(CurrenciesModel(0, "Fake AED currency"))
    )

    override fun getCurrencyExchangeRates(forceRefreshData: Boolean): Flow<Result<CurrencyRatesModel.Rates>> =
        flowOf(
            Result.success(
                CurrencyRatesModel.Rates(
                    0,
                    TestAmount,
                    TestAmount2
                )
            )
        )
}

const val TestAmount = 10.0
const val TestAmount2 = 20.0

const val FakeTestCurrencySymbol = "AED"
