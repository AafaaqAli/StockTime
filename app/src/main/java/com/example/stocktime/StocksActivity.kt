package com.example.stocktime

import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stocks.Constants
import com.example.stocks.StockApplicationClass
import kotlinx.android.synthetic.main.activity_stocks.*

class StocksActivity : WearableActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stocks)

        initAdapter()
    }


    private fun initAdapter() {
        if (StockApplicationClass.getStocksList().isNotEmpty()) {
            textViewSortedStocksItemNotAdded.visibility = View.INVISIBLE

            recyclerViewSortedData.layoutManager = LinearLayoutManager(this)
            recyclerViewSortedData.adapter = StockAdapter(
                this,
                StockApplicationClass.getStocksList()
            )
        }
    }

    override fun onResume() {
        super.onResume()
        initAdapter()
    }
}