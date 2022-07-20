package com.example.currencychallenge.utils

import java.util.*

fun Double.formatAmount(
    format: String = "%.3f",
): String = Formatter().format(format, this).toString()


