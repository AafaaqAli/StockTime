package com.example.stocks

import android.app.Application

class StockApplicationClass : Application() {
    companion object {
        private var listOfStocks: MutableList<Stock> = arrayListOf()
        private var listOfSelectedStocks: MutableList<Stock> = arrayListOf()

        fun getStocksList(): MutableList<Stock> {
            return this.listOfStocks
        }

        fun getStockItem(position: Int): Stock {
            return this.listOfStocks[position]
        }

        fun removeStockItem(stock: Stock) {
            listOfStocks.remove(stock)
        }
        fun addStockItem(stock: Stock){
            listOfStocks.add(stock)
        }


        fun getSelectedStocksList(): MutableList<Stock> {
            return this.listOfSelectedStocks
        }

        fun getSelectedStockItem(position: Int): Stock {
            return this.listOfSelectedStocks[position]
        }

        fun removeSelectedStockItem(stock: Stock) {
            listOfSelectedStocks.remove(stock)
        }
        fun addSelectedStockItem(stock: Stock){
            listOfSelectedStocks.add(stock)
        }
    }
}
