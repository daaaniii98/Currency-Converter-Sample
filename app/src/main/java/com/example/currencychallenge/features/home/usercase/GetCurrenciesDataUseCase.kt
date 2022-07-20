package com.example.currencychallenge.features.home.usercase

import com.example.currencychallenge.data.network.model.response.CurrenciesModel
import com.example.currencychallenge.data.repo.AppRepository
import com.example.currencychallenge.domain.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrenciesDataUseCase @Inject constructor(private val appRepo: AppRepository) :
    BaseUseCase<CurrenciesModel, BaseUseCase.None>() {

    override fun run(params: None): Flow<Result<CurrenciesModel>> = appRepo.getCurrencies()

}