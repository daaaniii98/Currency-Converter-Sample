package com.example.currencychallenge.features.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencychallenge.core.base.UiStateBase
import com.example.currencychallenge.data.network.model.response.CurrenciesModel
import com.example.currencychallenge.domain.BaseUseCase
import com.example.currencychallenge.features.home.usercase.GetCurrenciesDataUseCase
import com.example.currencychallenge.features.home.usercase.GetCurrencyExchangeDataUseCase
import com.example.currencychallenge.features.model.CurrencyExchangeRateModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCurrenciesDataUseCase: GetCurrenciesDataUseCase,
    private val getCurrencyExchangeDataUseCase: GetCurrencyExchangeDataUseCase
) :
    ViewModel() {

    private var searchJob: Job? = null
    private val selectedCurrencyParam = MutableStateFlow(CurrencyExchangeRateModel(Pair("", 0.0)))

    private val _homeUiStates =
        MutableStateFlow<UiStateBase>(UiStateBase.Default)
    val homeUiStates: StateFlow<UiStateBase> = _homeUiStates


    fun retry() {
        val currentErrorState = _homeUiStates.value
        if (currentErrorState is HomeFragmentUIStates.Error) {
            if (currentErrorState.errorType is HomeFragmentErrorTypes.CurrenciesError) {
                getCurrencies()
            } else {
                getCurrencyExchangeData()
            }
        }
    }

    init {
        getCurrencies()
        getCurrencyExchangeData()
    }

    private fun getCurrencyExchangeData() {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            selectedCurrencyParam.collectLatest {
                if (it.data.first.isNotEmpty() && it.data.second > 0.0) {
                    getCurrencyExchangeDataUseCase(
                        it
                    ).collectLatest { currencyExchangeResult ->
                        if (currencyExchangeResult.isSuccess) {
                            _homeUiStates.emit(
                                HomeFragmentUIStates.ShowCurrencyExchangeRates(
                                    currencyExchangeResult.getOrDefault(emptyList())
                                )
                            )
                        } else {
                            _homeUiStates.emit(
                                HomeFragmentUIStates.Error(
                                    HomeFragmentErrorTypes.GeneralError(
                                        currencyExchangeResult.exceptionOrNull()
                                    )
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getCurrencies() {
        viewModelScope.launch {
            _homeUiStates.tryEmit(HomeFragmentUIStates.Loading)
            getCurrenciesDataUseCase(BaseUseCase.None()).collect { result ->
                if (result.isSuccess) {
                    result.getOrNull()?.let { currenciesModel ->
                        _homeUiStates.tryEmit(HomeFragmentUIStates.ShowCurrencies(currenciesModel))
                    }
                } else {
                    _homeUiStates.tryEmit(
                        HomeFragmentUIStates.Error(
                            HomeFragmentErrorTypes.CurrenciesError(
                                result.exceptionOrNull()
                            )
                        )
                    )
                }
            }
        }
    }

    fun updateSelectCurrencySymbol(selectedItem: String?) {
        if (selectedItem.isNullOrEmpty()) {
            return
        }
        selectedCurrencyParam.update {
            CurrencyExchangeRateModel(Pair(selectedItem, it.data.second))
        }
    }

    fun updateSelectedCurrencyAmount(amount: Double) {
        if (amount <= 0.0) {
            return
        }
        selectedCurrencyParam.update {
            CurrencyExchangeRateModel(Pair(it.data.first, amount))
        }
    }
}

sealed class HomeFragmentUIStates : UiStateBase() {
    object Loading : HomeFragmentUIStates()
    data class Error(val errorType: HomeFragmentErrorTypes) : HomeFragmentUIStates()
    data class ShowCurrencies(val data: CurrenciesModel) : HomeFragmentUIStates()
    data class ShowCurrencyExchangeRates(val data: List<CurrencyExchangeRateModel>) :
        HomeFragmentUIStates()
}

sealed class HomeFragmentErrorTypes {
    data class GeneralError(val exception: Throwable?) : HomeFragmentErrorTypes()
    object CurrencyFieldEmptyError : HomeFragmentErrorTypes()
    data class CurrenciesError(val exception: Throwable?) : HomeFragmentErrorTypes()
}