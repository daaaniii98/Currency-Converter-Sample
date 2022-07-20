package com.example.currencychallenge.domain

import kotlinx.coroutines.flow.Flow

abstract class BaseUseCase<out Type, in Params> {

    abstract fun run(params: Params): Flow<Result<Type>>

    operator fun invoke(
        params: Params
    ) = run(params)

    class None

}