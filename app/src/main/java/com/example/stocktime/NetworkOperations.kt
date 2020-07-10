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

class NetworkOperations {
    fun startNetworkRequest() {
        Log.d(
            "networkingServiceLog",
            "RequestJobService: Job service running, Network Request Started"
        )
        GlobalScope.launch(Dispatchers.IO) {
            Constants.SYMBOL_LIST.forEach { symbol ->
                AndroidNetworking.get(" https://finnhub.io/api/v1/quote?symbol={symbol}&token={token}")
                    .addPathParameter("symbol", symbol)
                    .addPathParameter("token", Constants.API_TOKEN)
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsJSONObject(object : JSONObjectRequestListener {
                        override fun onResponse(response: JSONObject?) {
                            StockApplicationClass.addStockItem(
                                Stock(
                                    false,
                                    symbol,
                                    response!!.getString("o"),
                                    response.getString("h"),
                                    response.getString("l"),
                                    response.getString("c"),
                                    response.getString("pc"),
                                    StockCalculator.calculateProfitLoss(response.getString("o"), response.getString("c"))
                                )
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

        NetworkingHelperClass(Constants.Context).cancelJob()
        NetworkingHelperClass(Constants.Context).scheduleJob(true)
        Log.d(
            "networkingServiceLog",
            "RequestJobService: Job Finished And Rescheduled"
        )
    }
}