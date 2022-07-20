package com.example.currencychallenge.data.local.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.currencychallenge.data.network.model.response.CurrenciesModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM currencies")
    fun getCurrencies(): CurrenciesModel?

    /*
    To observe live database changes
     */
    @Query("SELECT * FROM currencies")
    fun getCurrenciesFlow(): Flow<CurrenciesModel?>


    @Insert(onConflict = REPLACE)
    suspend fun insertCurrencies(currencies: CurrenciesModel)

    @Transaction
    suspend fun deleteAndInsertCurrencies(currencies: CurrenciesModel){
        deleteCurrencies()
        insertCurrencies(currencies)
    }

    @Query("DELETE FROM currencies")
    suspend fun deleteCurrencies()

    @Update
    suspend fun updateCurrencies(currencies: CurrenciesModel)

}