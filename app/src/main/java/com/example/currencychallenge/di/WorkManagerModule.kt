package com.example.currencychallenge.di

import com.example.currencychallenge.core.workers.MyWorkManager
import com.example.currencychallenge.core.workers.MyWorkManagerImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class WorkManagerModule {
    @Singleton
    @Binds
    abstract fun provideMyWorkManager(
        myWorkManagerImp: MyWorkManagerImp
    ): MyWorkManager
}
