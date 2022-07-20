package com.example.currencychallenge.features.home.usercase

import com.example.currencychallenge.data.network.model.response.CurrencyRatesModel
import com.example.currencychallenge.data.repo.AppRepository
import com.example.currencychallenge.domain.BaseUseCase
import com.example.currencychallenge.features.model.CurrencyExchangeRateModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class GetCurrencyExchangeDataUseCase @Inject constructor(private val appRepo: AppRepository) :
    BaseUseCase<List<CurrencyExchangeRateModel>, CurrencyExchangeRateModel>() {

    override fun run(params: CurrencyExchangeRateModel): Flow<Result<List<CurrencyExchangeRateModel>>> =
        appRepo.getCurrencyExchangeRates()
            .transform {
                if (it.isSuccess) {
                    it.getOrNull()?.let { data ->
                        emit(
                            Result.success(
                                CurrencyRatesModel.Rates.getCurrencyExchangeRateList(
                                    data,
                                    params.data.first,
                                    params.data.second
                                )
                            )
                        )
                    }
                } else {
                    emit(Result.failure(it.exceptionOrNull() ?: UnknownError()))
                }
            }
}