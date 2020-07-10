package com.example.stocktime
import java.lang.Math.round

class StockCalculator {
    companion object {
        fun calculateProfitLoss(o: String, c: String): Boolean{
            return c.toDouble() > o.toDouble()
        }

        fun calculatePercentage(o: String, c: String): String{
            return (round(o.toDouble() / c.toDouble() * 100.0) / 100.0).toString()
        }
    }
}