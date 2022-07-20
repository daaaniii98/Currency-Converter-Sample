package com.example.currencychallenge.di

import android.content.Context
import androidx.room.Room
import com.example.currencychallenge.data.local.dao.CurrencyDao
import com.example.currencychallenge.data.local.dao.CurrencyRatesDao
import com.example.currencychallenge.data.local.database.AppDatabase
import com.example.currencychallenge.data.network.NetworkService
import com.example.currencychallenge.data.repo.AppRepository
import com.example.currencychallenge.data.repo.AppRepositoryImpl
import com.example.currencychallenge.utils.NetworkHandler
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DatabaseName
        ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideCurrencyDao(database: AppDatabase): CurrencyDao = database.currencyDao

    @Singleton
    @Provides
    fun provideCurrencyExchangeDao(database: AppDatabase): CurrencyRatesDao =
        database.currencyRatesDao
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DatabaseBindingModule {
    @Singleton
    @Binds
    abstract fun provideAppRepository(
        appRepositoryImpl: AppRepositoryImpl
    ): AppRepository
}

const val DatabaseName = "app_database"