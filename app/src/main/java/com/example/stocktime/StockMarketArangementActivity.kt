package com.example.stocktime

import android.content.Intent
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.view.View
import com.example.stocks.Constants
import kotlinx.android.synthetic.main.activity_stock_market_arangement.*

class StockMarketArangementActivity : WearableActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_market_arangement)

        cardViewGeneral.setOnClickListener(this)
        cardViewTSX.setOnClickListener(this)
        cardViewNYSE.setOnClickListener(this)
        cardViewNASDAK.setOnClickListener(this)


    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.cardViewGeneral -> {
                getAppropriateSymbolList(Constants.GENERAL, "GENERAL")
            }

            R.id.cardViewTSX -> {
                getAppropriateSymbolList(Constants.TSX, "TSX")
            }

            R.id.cardViewNYSE -> {
                getAppropriateSymbolList(Constants.NYSE, "NYSE")

            }

            R.id.cardViewNASDAK -> {
                getAppropriateSymbolList(Constants.NASDAK, "NASDAK")

            }
        }
    }


    private fun getAppropriateSymbolList(stockExchangeID: Int, stockMarketName: String) {
       val intent = Intent(this, ArrangementAlphabeticallyActivity::class.java)
        intent.putExtra("stock_exchange_id", stockExchangeID)
        intent.putExtra("stock_exchange_name", stockMarketName)
        startActivity(intent)
    }
}