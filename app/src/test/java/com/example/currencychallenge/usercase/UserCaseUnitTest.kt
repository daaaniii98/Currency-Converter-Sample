package com.example.currencychallenge.usercase

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.currencychallenge.data.network.model.response.CurrenciesModel
import com.example.currencychallenge.domain.BaseUseCase
import com.example.currencychallenge.features.home.usercase.GetCurrenciesDataUseCase
import com.example.currencychallenge.features.home.usercase.GetCurrencyExchangeDataUseCase
import com.example.currencychallenge.features.model.CurrencyExchangeRateModel
import com.example.currencychallenge.repo.FakeAppRepository
import com.example.currencychallenge.repo.TestAmount
import com.example.currencychallenge.repo.FakeTestCurrencySymbol
import com.example.currencychallenge.repo.TestAmount2
import com.example.currencychallenge.utils.CoroutineTestRule
import com.example.currencychallenge.utils.runBlockingTest
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.math.roundToInt

@RunWith(MockitoJUnitRunner::class)
class UserCaseUnitTest : TestCase() {

    @ExperimentalCoroutinesApi
    @get:Rule
    val testCoroutineRule = CoroutineTestRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var getCurrencyExchangeDataUseCase: GetCurrencyExchangeDataUseCase
    private lateinit var getCurrenciesDataUseCase: GetCurrenciesDataUseCase
    private val appRepository = FakeAppRepository()

    @Before
    fun before() {
        getCurrencyExchangeDataUseCase = GetCurrencyExchangeDataUseCase(appRepository)
        getCurrenciesDataUseCase = GetCurrenciesDataUseCase(appRepository)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `test get currencies use-case`() = testCoroutineRule.runBlockingTest {
        // given
        val currencies = getCurrenciesDataUseCase(BaseUseCase.None()).first().getOrElse {
            throw Exception("Currencies must not be empty or null")
        }
        // when
        val listOfCurrencies = CurrenciesModel.getAvailableCurrencies(currencies)

        // then
        assertTrue(listOfCurrencies.isNotEmpty())
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `test get-currency-exchange-rate use-case`() = testCoroutineRule.runBlockingTest {
        // given
        val testInputAmount = 3.0
        val testData = CurrencyExchangeRateModel(
            Pair(
                FakeTestCurrencySymbol, testInputAmount
            )
        )
        // when
        val currencyExchangeData = getCurrencyExchangeDataUseCase(
            testData
        ).first().getOrElse {
            throw Exception("Currency exchange data must not be empty or null")
        }

        // then
        assertEquals(testInputAmount.roundToInt(), currencyExchangeData[0].data.second.roundToInt())
        assertEquals(((1 / TestAmount) * testInputAmount * TestAmount2).roundToInt(), currencyExchangeData[1].data.second.roundToInt())
    }

}