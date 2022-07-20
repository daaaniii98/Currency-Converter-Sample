package com.example.currencychallenge.data.local.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.currencychallenge.data.network.model.response.CurrencyRatesModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyRatesDao {
    @Query("SELECT * FROM currency_rates")
    fun getCurrencyRates(): CurrencyRatesModel.Rates?

    /*
    To observe live database changes
     */
    @Query("SELECT * FROM currency_rates")
    fun getCurrencyRatesFlow(): Flow<CurrencyRatesModel.Rates?>


    @Insert(onConflict = REPLACE)
    suspend fun insertCurrencyRates(currencies: CurrencyRatesModel.Rates)

    @Transaction
    suspend fun deleteAndInsertCurrencyRates(currencies: CurrencyRatesModel.Rates){
        deleteCurrencyRates()
        insertCurrencyRates(currencies)
    }

    @Query("DELETE FROM currency_rates")
    suspend fun deleteCurrencyRates()

}