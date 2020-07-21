package com.stockwatch.stockWatchIt

class StockCalculator {
    companion object {
        fun calculateProfitLoss(o: String, c: String): Boolean{
            return c.toDouble() > o.toDouble()
        }

        fun calculatePercentage(o: String, c: String): String{
            if(o != "NULL" && c != "NULL"){
                return ("%.3f".format (((c.toFloat() - o.toFloat()) / o.toFloat()) * 100.0))
            }else{
                return "NULL"
            }
        }
    }
}