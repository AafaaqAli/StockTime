package com.stockwatch.stocks

import android.app.Application
import com.stockwatch.stockWatchIt.RawStock

class StockApplicationClass : Application() {
    companion object {
        private var listOfStocks: ArrayList<Stock> = arrayListOf()
        private var listOfSelectedStocks: ArrayList<Stock> = arrayListOf()
        private var listOfRawSelectedStocks: ArrayList<RawStock> = arrayListOf()

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

        fun emptyStockItem(){
            listOfStocks.clear()
        }


        fun getSelectedStocksList(): ArrayList<Stock> {
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


        fun getSelectedRowStocksList(): ArrayList<RawStock> {
            return this.listOfRawSelectedStocks
        }

        fun getSelectedRawStockItem(position: Int): RawStock {
            return this.listOfRawSelectedStocks[position]
        }
        fun removeSelectedRawStockItem(stock: RawStock) {
            listOfRawSelectedStocks.remove(stock)
        }
        fun addSelectedRawStockItem(stock: RawStock){
            listOfRawSelectedStocks.add(stock)
        }
    }
}
