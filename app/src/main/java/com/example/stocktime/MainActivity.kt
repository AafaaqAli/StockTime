package com.example.stocktime

import android.content.Intent
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidnetworking.AndroidNetworking
import com.example.stocks.Constants
import com.example.stocks.StockApplicationClass
import com.jacksonandroidnetworking.JacksonParserFactory
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : WearableActivity() {
    lateinit var networkingHelperClass: NetworkingHelperClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(HelperClass.isInternetAvailable(this)){
            AndroidNetworking.initialize(applicationContext);
            AndroidNetworking.setParserFactory(JacksonParserFactory())

        }
        networkingHelperClass = NetworkingHelperClass(this)
        Constants.Context = applicationContext

        if (HelperClass.isInternetAvailable(this)) {
            if (!Constants.isFirstRun) {
                if (StockApplicationClass.getSelectedStocksList().isNotEmpty()) {
                    textViewItemsNotFound.setText(R.string.no_item_added)
                    textViewItemsNotFound.visibility = View.INVISIBLE
                    networkingHelperClass.startJob()

                    initAdapter()
                } else {
                    textViewItemsNotFound.setText(R.string.no_item_added)
                    textViewItemsNotFound.visibility = View.VISIBLE
                    NetworkOperations().startNetworkRequest()
                }
            } else {
                if (StockApplicationClass.getSelectedStocksList().isEmpty()) {
                    Constants.isFirstRun = true
                    NetworkOperations().startNetworkRequest()
                    textViewItemsNotFound.setText(R.string.no_item)
                    textViewItemsNotFound.visibility = View.VISIBLE
                }
            }
        }
        openSetting()
    }


    private fun openSetting() {
        imageViewSetting.setOnClickListener {
            startActivity(Intent(this, StocksActivity::class.java))
        }
    }


    private fun initAdapter() {
        /*set adapter here*/
        recyclerViewSelectedStock.layoutManager = LinearLayoutManager(this)
        recyclerViewSelectedStock.adapter = SelectedStockAdapter(
            this,
            StockApplicationClass.getSelectedStocksList())
    }

    override fun onResume() {
        super.onResume()
        if (StockApplicationClass.getSelectedStocksList().isNotEmpty()) {
            textViewItemsNotFound.visibility = View.INVISIBLE
            initAdapter()

        } else {
            /*Show No Data found*/
            if (!Constants.isFirstRun) {
                textViewItemsNotFound.setText(R.string.no_item_added)
                textViewItemsNotFound.visibility = View.INVISIBLE
            } else {
                Constants.isFirstRun = true
                textViewItemsNotFound.setText(R.string.no_item)
                textViewItemsNotFound.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (HelperClass.isJobServiceOn(this)) {
            networkingHelperClass.cancelJob()
        }
    }
}
