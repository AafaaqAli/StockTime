package com.example.stocks

import android.app.Application

class StockApplicationClass : Application() {
    companion object {
        private var listOfStocks: MutableList<Stock> = arrayListOf()
        private var listOfSelectedStocks: MutableList<Stock> = arrayListOf()
        private var listOfSortedStocks: MutableList<Stock> = arrayListOf()

        fun getStocksList(): MutableList<Stock> {
            return this.listOfStocks
        }

        fun getStockItem(position: Int): Stock {
            return this.listOfStocks[position]
        }

        fun removeStockItem(stock: Stock) {
            listOfStocks.remove(stock)
        }


        fun getSeletedStocksList(): MutableList<Stock> {
            return this.listOfStocks
        }

        fun getSelectedStockItem(position: Int): Stock {
            return this.listOfStocks[position]
        }

        fun removeSelectedStockItem(stock: Stock) {
            listOfStocks.remove(stock)
        }


        fun getSortedStocksList(): MutableList<Stock> {
            return this.listOfStocks
        }

        fun getSortedStockItem(position: Int): Stock {
            return this.listOfStocks[position]
        }

        fun removeSortedStockItem(stock: Stock) {
            listOfStocks.remove(stock)
        }


    }


}
