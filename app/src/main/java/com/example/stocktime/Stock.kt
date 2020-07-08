package com.example.stocks

import androidx.annotation.Nullable

data class Stock(@Nullable var isSelected: Boolean, @Nullable var stockName: String, @Nullable var points: String, @Nullable var isUp: Boolean)