package com.example.currencychallenge.features.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.currencychallenge.databinding.RvItemExchangeRateBinding
import com.example.currencychallenge.features.model.CurrencyExchangeRateModel
import com.example.currencychallenge.utils.formatAmount
import java.util.*

class CurrencyExchangeAdapter(private val itemClick: (CurrencyExchangeRateModel) -> Unit) :
    ListAdapter<CurrencyExchangeRateModel, CurrencyExchangeAdapter.ViewHolder>(
        CurrencyExchangeRateModel.diffUtils
    ) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        RvItemExchangeRateBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    inner class ViewHolder(private val itemViewBinding: RvItemExchangeRateBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {

        fun bind(data: CurrencyExchangeRateModel) = with(itemViewBinding) {
            txCurrencySymbol.text = data.data.first
            txCurrencyRate.text = data.data.second.formatAmount()
            root.setOnClickListener {
                itemClick.invoke(data)
            }
        }
    }

}