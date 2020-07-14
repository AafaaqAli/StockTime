package com.example.stocks

import androidx.annotation.Nullable

data class Stock(var isSelected: Boolean,
                 var symbol: String,
                 var openPrice: String,
                 var highPrice: String,
                 var lowPrice: String,
                 var currentPrice: String,
                 var previousPrice: String,
                 var isUp: Boolean)