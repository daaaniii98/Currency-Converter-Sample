package com.example.currencychallenge.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.currencychallenge.data.local.dao.CurrencyDao
import com.example.currencychallenge.data.local.dao.CurrencyRatesDao
import com.example.currencychallenge.data.network.model.response.CurrenciesModel
import com.example.currencychallenge.data.network.model.response.CurrencyRatesModel

@Database(
    version = 3,
    entities = [CurrenciesModel::class, CurrencyRatesModel.Rates::class],
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract val currencyDao: CurrencyDao
    abstract val currencyRatesDao: CurrencyRatesDao
}