package com.stockwatch.stockWatchIt

import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.stockwatch.stocks.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject

class NetworkOperations {
    fun startNetworkRequest() {
        StockApplicationClass.getStocksList().clear()

        GlobalScope.launch {
            StockApplicationClass.getStocksList().clear()
        }

        GlobalScope.launch(Dispatchers.IO) {
            Log.d("networkingServiceLog", "Size of SelectedRawList: " + StockApplicationClass.getSelectedRowStocksList().size)
            StockApplicationClass.getSelectedRowStocksList().forEach { rawStock ->
                AndroidNetworking.get(" https://finnhub.io/api/v1/quote?symbol={symbol}&token={token}")
                        .addPathParameter("symbol", rawStock.symbol)
                        .addPathParameter("token", Constants.API_TOKEN)
                        .setPriority(Priority.HIGH)
                        .build()
                        .getAsJSONObject(object : JSONObjectRequestListener {
                            override fun onResponse(response: JSONObject?) {
                                if (response?.has("error") != true) {
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
    }
}