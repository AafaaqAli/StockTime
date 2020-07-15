package com.example.stocktime

import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.stocks.Constants
import com.example.stocks.Stock
import com.example.stocks.StockApplicationClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject

class NetworkOperations (rawStocks: List<RawStock>){
    private val mRawStocks = rawStocks
    fun startNetworkRequest() {
        Log.d(
                "networkingServiceLog",
                "NetworkOperation:  Network Request Started"
        )
        GlobalScope.launch(Dispatchers.IO) {
            StockApplicationClass.emptyStockItem()
            Log.d("networkingServiceLog", "Size of SelectedRawList: " + StockApplicationClass.getSelectedRowStocksList().size)
            mRawStocks.forEach { rawStock ->
                AndroidNetworking.get(" https://finnhub.io/api/v1/quote?symbol={symbol}&token={token}")
                        .addPathParameter("symbol", rawStock.symbol)
                        .addPathParameter("token", Constants.API_TOKEN)
                        .setPriority(Priority.HIGH)
                        .build()
                        .getAsJSONObject(object : JSONObjectRequestListener {
                            override fun onResponse(response: JSONObject?) {
                                StockApplicationClass.addStockItem(
                                        Stock(
                                                false,
                                                rawStock.symbol,
                                                response!!.getString("o"),
                                                response.getString("h"),
                                                response.getString("l"),
                                                response.getString("c"),
                                                response.getString("pc"),
                                                StockCalculator.calculateProfitLoss(response.getString("o"), response.getString("c"))
                                        )
                                )

                                Log.d("networkingServiceLog", "Symbol: " + rawStock.symbol + "\nO: " + response!!.getString("o") + "\nH: " + response.getString("h") +
                                        "\nL: " + response.getString("l") + "\nC: " + response.getString("c") + "\nPC: " + response.getString("pc")
                                )
                            }

                            override fun onError(error: ANError) {
                                Log.d(
                                        "networkingServiceLog",
                                        "RequestJobService: Error while Android Networking, " + error.message
                                )
                            }
                        })
            }
        }
/*
        NetworkingHelperClass(Constants.Context).cancelJob()
        NetworkingHelperClass(Constants.Context).scheduleJob(true)*/
    }
}