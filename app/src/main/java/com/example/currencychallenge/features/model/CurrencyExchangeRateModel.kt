package com.example.currencychallenge.features.model

import androidx.recyclerview.widget.DiffUtil

@JvmInline
value class CurrencyExchangeRateModel(val data: Pair<String, Double>) {
    companion object {
        val diffUtils = object : DiffUtil.ItemCallback<CurrencyExchangeRateModel>() {
            override fun areItemsTheSame(
                oldItem: CurrencyExchangeRateModel,
                newItem: CurrencyExchangeRateModel
            ): Boolean = oldItem.data.second == newItem.data.second

            override fun areContentsTheSame(
                oldItem: CurrencyExchangeRateModel,
                newItem: CurrencyExchangeRateModel
            ): Boolean =
                oldItem.data.first == newItem.data.first
        }
    }
}