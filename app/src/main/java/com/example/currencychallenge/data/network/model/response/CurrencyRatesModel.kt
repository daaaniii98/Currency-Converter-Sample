package com.example.currencychallenge.data.network.model.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.currencychallenge.features.model.CurrencyExchangeRateModel
import kotlin.reflect.full.declaredMemberProperties

data class CurrencyRatesModel(
    val base: String? = null,
    val disclaimer: String? = null,
    val license: String? = null,
    val rates: Rates,
    val timestamp: Double? = null
) {
    @Entity(tableName = "currency_rates")
    data class Rates(
        @PrimaryKey(autoGenerate = true)
        val primaryKey: Long,
        val AED: Double? = null,
        val AFN: Double? = null,
        val ALL: Double? = null,
        val AMD: Double? = null,
        val ANG: Double? = null,
        val AOA: Double? = null,
        val ARS: Double? = null,
        val AUD: Double? = null,
        val AWG: Double? = null,
        val AZN: Double? = null,
        val BAM: Double? = null,
        val BBD: Double? = null,
        val BDT: Double? = null,
        val BGN: Double? = null,
        val BHD: Double? = null,
        val BIF: Double? = null,
        val BMD: Double? = null,
        val BND: Double? = null,
        val BOB: Double? = null,
        val BRL: Double? = null,
        val BSD: Double? = null,
        val BTC: Double? = null,
        val BTN: Double? = null,
        val BWP: Double? = null,
        val BYN: Double? = null,
        val BZD: Double? = null,
        val CAD: Double? = null,
        val CDF: Double? = null,
        val CHF: Double? = null,
        val CLF: Double? = null,
        val CLP: Double? = null,
        val CNH: Double? = null,
        val CNY: Double? = null,
        val COP: Double? = null,
        val CRC: Double? = null,
        val CUC: Double? = null,
        val CUP: Double? = null,
        val CVE: Double? = null,
        val CZK: Double? = null,
        val DJF: Double? = null,
        val DKK: Double? = null,
        val DOP: Double? = null,
        val DZD: Double? = null,
        val EGP: Double? = null,
        val ERN: Double? = null,
        val ETB: Double? = null,
        val EUR: Double? = null,
        val FJD: Double? = null,
        val FKP: Double? = null,
        val GBP: Double? = null,
        val GEL: Double? = null,
        val GGP: Double? = null,
        val GHS: Double? = null,
        val GIP: Double? = null,
        val GMD: Double? = null,
        val GNF: Double? = null,
        val GTQ: Double? = null,
        val GYD: Double? = null,
        val HKD: Double? = null,
        val HNL: Double? = null,
        val HRK: Double? = null,
        val HTG: Double? = null,
        val HUF: Double? = null,
        val IDR: Double? = null,
        val ILS: Double? = null,
        val IMP: Double? = null,
        val INR: Double? = null,
        val IQD: Double? = null,
        val IRR: Double? = null,
        val ISK: Double? = null,
        val JEP: Double? = null,
        val JMD: Double? = null,
        val JOD: Double? = null,
        val JPY: Double? = null,
        val KES: Double? = null,
        val KGS: Double? = null,
        val KHR: Double? = null,
        val KMF: Double? = null,
        val KPW: Double? = null,
        val KRW: Double? = null,
        val KWD: Double? = null,
        val KYD: Double? = null,
        val KZT: Double? = null,
        val LAK: Double? = null,
        val LBP: Double? = null,
        val LKR: Double? = null,
        val LRD: Double? = null,
        val LSL: Double? = null,
        val LYD: Double? = null,
        val MAD: Double? = null,
        val MDL: Double? = null,
        val MGA: Double? = null,
        val MKD: Double? = null,
        val MMK: Double? = null,
        val MNT: Double? = null,
        val MOP: Double? = null,
        val MRU: Double? = null,
        val MUR: Double? = null,
        val MVR: Double? = null,
        val MWK: Double? = null,
        val MXN: Double? = null,
        val MYR: Double? = null,
        val MZN: Double? = null,
        val NAD: Double? = null,
        val NGN: Double? = null,
        val NIO: Double? = null,
        val NOK: Double? = null,
        val NPR: Double? = null,
        val NZD: Double? = null,
        val OMR: Double? = null,
        val PAB: Double? = null,
        val PEN: Double? = null,
        val PGK: Double? = null,
        val PHP: Double? = null,
        val PKR: Double? = null,
        val PLN: Double? = null,
        val PYG: Double? = null,
        val QAR: Double? = null,
        val RON: Double? = null,
        val RSD: Double? = null,
        val RUB: Double? = null,
        val RWF: Double? = null,
        val SAR: Double? = null,
        val SBD: Double? = null,
        val SCR: Double? = null,
        val SDG: Double? = null,
        val SEK: Double? = null,
        val SGD: Double? = null,
        val SHP: Double? = null,
        val SLL: Double? = null,
        val SOS: Double? = null,
        val SRD: Double? = null,
        val SSP: Double? = null,
        val STD: Double? = null,
        val STN: Double? = null,
        val SVC: Double? = null,
        val SYP: Double? = null,
        val SZL: Double? = null,
        val THB: Double? = null,
        val TJS: Double? = null,
        val TMT: Double? = null,
        val TND: Double? = null,
        val TOP: Double? = null,
        val TRY: Double? = null,
        val TTD: Double? = null,
        val TWD: Double? = null,
        val TZS: Double? = null,
        val UAH: Double? = null,
        val UGX: Double? = null,
        val USD: Double? = null,
        val UYU: Double? = null,
        val UZS: Double? = null,
        val VES: Double? = null,
        val VND: Double? = null,
        val VUV: Double? = null,
        val WST: Double? = null,
        val XAF: Double? = null,
        val XAG: Double? = null,
        val XAU: Double? = null,
        val XCD: Double? = null,
        val XDR: Double? = null,
        val XOF: Double? = null,
        val XPD: Double? = null,
        val XPF: Double? = null,
        val XPT: Double? = null,
        val YER: Double? = null,
        val ZAR: Double? = null,
        val ZMW: Double? = null,
        val ZWL: Double? = null
    ) {
        companion object {
            private fun getSelectedCurrencyRate(obj: Rates, selectedCurrency: String): Double? {
                for (data in Rates::class.declaredMemberProperties) {
                    val myObj = data.get(obj)
                    if (myObj is Double? && myObj != null && data.name == selectedCurrency) {
                        return myObj
                    }
                }
                return null
            }

            fun getCurrencyExchangeRateList(
                obj: Rates,
                selectedCurrency: String,
                amount: Double
            ): MutableList<CurrencyExchangeRateModel> {
                val currencyList = mutableListOf<CurrencyExchangeRateModel>()
                getSelectedCurrencyRate(obj, selectedCurrency)?.let { selectedSymbolRate ->
                    val usdPerSelectedSymbolRate = 1 / selectedSymbolRate
                    for (data in Rates::class.declaredMemberProperties) {
                        val myObj = data.get(obj)
                        if (myObj is Double? && myObj != null) {
                            currencyList.add(CurrencyExchangeRateModel(Pair(data.name, usdPerSelectedSymbolRate * (myObj * amount) )))
                        }
                    }
                }
                return currencyList
            }
        }
    }
}