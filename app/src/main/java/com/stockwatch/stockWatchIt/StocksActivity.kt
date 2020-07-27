package com.stockwatch.stockWatchIt

import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.stockwatch.stocks.Constants
import kotlinx.android.synthetic.main.activity_stocks.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList

class StocksActivity : WearableActivity() {
    var stockMarketID = -1
    var tempStocksList: ArrayList<RawStock> = arrayListOf()
    var alphabet: String = "null"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stocks)

        getIntentData()
        setDataPreferences(alphabet)
    }


    private fun getIntentData() {
        stockMarketID = intent.getIntExtra("stockListID", -1)
        alphabet = intent.getStringExtra("stockListAlphabet").toString()

    }

    private fun initAdapter(stockList: ArrayList<RawStock>) {
        if (stockList.isNotEmpty()) {
            textViewSortedStocksItemNotAdded.visibility = View.INVISIBLE

            GlobalScope.launch(Dispatchers.Main) {
                recyclerViewSortedData.layoutManager = LinearLayoutManager(this@StocksActivity)
                recyclerViewSortedData.adapter = StockAdapter(
                        this@StocksActivity,
                        stockList
                )
            }

        }
    }

    private fun setDataPreferences(tag: String) {
        var counter = 0
        var stockMarketList: ArrayList<String> = arrayListOf()
        var isListValid = true
        when (stockMarketID) {
            Constants.FAVOURITE -> {
                isListValid = true
                StockApplicationClass.getSelectedRowStocksList().forEach { rawStock ->
                    stockMarketList.add(rawStock.symbol)
                    Log.d("bugLog", " In Stock Activity Added ${counter++}")
                }
            }

            Constants.NASDAQ -> {
                isListValid = true
                stockMarketList = Constants.NASDAK_SYMBOL_LIST
            }

            Constants.NYSE -> {
                isListValid = true
                stockMarketList = Constants.NYSE_SYMBOL_LIST
            }

            Constants.TSX -> {
                isListValid = true
                stockMarketList = Constants.TSX_SYMBOL_LIST
            }

            -1 -> {
                isListValid = false
            }
        }

        if (isListValid) {
            if (tag != "null") {
                if (tag == "FAVOURITE") {
                    GlobalScope.launch(Dispatchers.Default) {
                        for (symbol in stockMarketList) {
                            tempStocksList.add(RawStock(true, symbol))

                        }

                        initAdapter(tempStocksList)

                    }
                } else {
                    GlobalScope.launch(Dispatchers.Default) {
                        for (symbol in stockMarketList) {
                            if (symbol.startsWith(tag)) {
                                tempStocksList.add(RawStock(false, symbol))
                            }
                        }

                        initAdapter(tempStocksList)

                    }
                }
            } else {
                Log.d("LogAlpabet", "null")

            }

        }
    }
}