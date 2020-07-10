package com.example.stocks

import android.content.Context

class Constants {
    companion object {
        lateinit var Context: Context
        const val API_TOKEN = "bs3m6jfrh5r9llvji8sg"
        const val JOB_SERVICE_ID = 110
        const val JOB_REQUEST_RESTART_INTERVALS: Long = 1000 * 60 * 15 //15 minutes
        var isFirstRun: Boolean = false
        var SYMBOL_LIST: ArrayList<String> = arrayListOf(
            "AAPL",
            "MSFT",
            "AMZN",
            "GOOGL",
            "FB",
            "BABA",
            "BRK.A",
            "V",
            "JNJ",
            "WMT",
            "TSM",
            "MA",
            "PG",
            "JPM",
            "UNH",
            "HD",
            "TSLA",
            "NVDA",
            "INTC",
            "VZ",
            "NFLX",
            "ADBE",
            "T",
            "PYPL",
            "DIS",
            "NVS",
            "BAC",
            "MRK",
            "KO",
            "CSCO",
            "PFE",
            "PEP",
            "XOM",
            "CMCSA",
            "CRM",
            "SAP",
            "TM",
            "ABBV",
            "ORCL",
            "ASML",
            "ABT",
            "CVX",
            "LLY",
            "NKE",
            "TMO",
            "CHL",
            "TMUS",
            "AMGN",
            "AZN",
            "COST"
        )
    }
}