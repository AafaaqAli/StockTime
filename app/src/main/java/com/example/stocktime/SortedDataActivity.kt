package com.example.stocktime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.view.View
import com.example.stocks.Constants
import com.example.stocks.StockApplicationClass
import kotlinx.android.synthetic.main.activity_sorted_data.*

class SortedDataActivity : WearableActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sorted_data)

        initAdapter()
    }


    private fun initAdapter() {
        if (StockApplicationClass.getSortedStocksList().isNotEmpty()) {
            textViewSortedStocksItemNotAdded.visibility = View.INVISIBLE
            /*set Adapter here*/

        } else {
            textViewSortedStocksItemNotAdded.visibility = View.VISIBLE
            if (Constants.isFirstRun) {
                textViewSortedStocksItemNotAdded.setText(R.string.no_item)
            } else {
                textViewSortedStocksItemNotAdded.setText(R.string.no_item_added)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        initAdapter()
    }
}