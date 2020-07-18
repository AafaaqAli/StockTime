package com.example.stocktime

class StockCalculator {
    companion object {
        fun calculateProfitLoss(o: String, c: String): Boolean{
            return c.toDouble() > o.toDouble()
        }

        fun calculatePercentage(o: String, c: String): String{
            if(o != "NULL" && c != "NULL"){
                return ("%.3f".format (((c.toDouble() - o.toDouble()) / o.toDouble()) * 100.0))
            }else{
                return "NULL"
            }
        }
    }
}