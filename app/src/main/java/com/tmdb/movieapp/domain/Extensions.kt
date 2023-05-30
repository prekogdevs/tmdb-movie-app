package com.tmdb.movieapp.domain

import java.text.NumberFormat
import java.util.Currency

fun Number.formatToUSD(): String {
    val numberFormat: NumberFormat = NumberFormat.getCurrencyInstance()
    numberFormat.maximumFractionDigits = 0
    numberFormat.currency = Currency.getInstance("USD")
    return numberFormat.format(this)
}