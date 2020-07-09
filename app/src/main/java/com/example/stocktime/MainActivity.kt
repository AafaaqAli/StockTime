package com.example.stocktime

import android.content.Intent
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.util.Log
import android.view.View
import com.androidnetworking.AndroidNetworking
import com.example.stocks.Constants
import com.example.stocks.StockApplicationClass
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : WearableActivity() {
    lateinit var networkingHelperClass: NetworkingHelperClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidNetworking.initialize(applicationContext);
        networkingHelperClass = NetworkingHelperClass(this)
        Constants.Context = applicationContext



        if (!Constants.isFirstRun) {
            if (StockApplicationClass.getSeletedStocksList().isNotEmpty()) {
                textViewItemsNotFound.setText(R.string.no_item_added)
                textViewItemsNotFound.visibility = View.VISIBLE
                networkingHelperClass.startJob()
                initAdapter()
            } else {
                NetworkOperations().startNetworkRequest()
            }
        } else {
            if (StockApplicationClass.getSeletedStocksList().isEmpty()) {
                Constants.isFirstRun = true
                NetworkOperations().startNetworkRequest()
                textViewItemsNotFound.setText(R.string.no_item)
                textViewItemsNotFound.visibility = View.VISIBLE
            }
        }

        /*start Job*/
        if (StockApplicationClass.getStocksList().isEmpty()) {
            if (!HelperClass.isJobServiceOn(applicationContext)) {
                networkingHelperClass.startJob()
            } else {
                Log.d("networkingServiceLog", "MainActivity: Job service already running")
            }

        } else {
            /*show data*/
            initAdapter()
        }

        openSetting()

    }


    private fun openSetting() {
        imageViewSetting.setOnClickListener {
            startActivity(Intent(this, SortActivity::class.java))
        }
    }


    private fun initAdapter() {
        /*set adapter here*/
    }

    override fun onResume() {
        super.onResume()
        if (StockApplicationClass.getSeletedStocksList().isNotEmpty()) {
            textViewItemsNotFound.visibility = View.INVISIBLE
            /*InitAdapter here*/

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
