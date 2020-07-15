package com.example.stocktime

import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stocks.Constants
import com.example.stocks.Stock
import kotlinx.android.synthetic.main.activity_stocks.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class StocksActivity : WearableActivity() {
    var stockMarketID = -1
    var tempStocksList: ArrayList<RawStock> = arrayListOf()
    var alphabet: String = "non_null"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stocks)


        getIntentData()
        setDataPreferences(alphabet)
    }


    private fun getIntentData() {
        alphabet = intent.getStringExtra("stockListAlphabet").toString()
        stockMarketID = intent.getIntExtra("stockListID", -1)
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
        var stockMarketList: ArrayList<String> = arrayListOf()
        var isListValid = true
        when (stockMarketID) {
            Constants.GENERAL -> {
                isListValid = true
                stockMarketList = Constants.GENERAL_SYMBOL_LIST
            }

            Constants.NASDAK -> {
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
            if (tag != "non_null") {
                GlobalScope.launch(Dispatchers.Default) {
                    for (symbol in stockMarketList) {
                        if (symbol.startsWith(tag)) {
                            if (tempStocksList.size == 0) {
                                tempStocksList.add(RawStock(0, false, symbol))
                            } else {
                                tempStocksList.add(RawStock(tempStocksList.size + 1, false, symbol))
                            }

                        }
                    }

                    initAdapter(tempStocksList)

                }
            } else {
                Log.d("LogAlpabet", "null")

            }

        }
    }
}