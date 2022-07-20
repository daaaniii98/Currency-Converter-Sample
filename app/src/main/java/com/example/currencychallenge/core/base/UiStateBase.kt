package com.example.currencychallenge.core.base

import kotlin.random.Random

open class UiStateBase{
    object Default : UiStateBase()

    /*
    Override these methods to forcefully emit values
    even when they are same as previous ones
     */
    override fun equals(other: Any?): Boolean {
        return false
    }

    override fun hashCode(): Int {
        return Random.nextInt()
    }
}
