package com.stockwatch.stockWatchIt

import android.app.Application

class StockApplicationClass : Application() {
    companion object {
        private var listOfStocks: ArrayList<Stock> = arrayListOf()
        private var listOfRawSelectedStocks: ArrayList<RawStock> = arrayListOf()

        fun getStocksList(): MutableList<Stock> {
            return listOfStocks
        }

        fun addStockItem(stock: Stock){
            listOfStocks.add(stock)
        }

        fun getSelectedRowStocksList(): ArrayList<RawStock> {
            return listOfRawSelectedStocks
        }

        fun removeSelectedRawStockItem(stock: RawStock) {
            listOfRawSelectedStocks.remove(stock)
        }

        fun addSelectedRawStockItem(stock: RawStock){
            listOfRawSelectedStocks.add(stock)
        }
    }
}
