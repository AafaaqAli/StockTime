package com.stockwatch.stockWatchIt

import android.content.Intent
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidnetworking.AndroidNetworking
import com.stockwatch.stocks.Constants
import com.jacksonandroidnetworking.JacksonParserFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : WearableActivity() {
    private lateinit var networkingHelperClass: NetworkingHelperClass
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(HelperClass.isInternetAvailable(this)){
            AndroidNetworking.initialize(applicationContext)
            AndroidNetworking.setParserFactory(JacksonParserFactory())

        }
        networkingHelperClass = NetworkingHelperClass(this)
        Constants.Context = this.applicationContext
        openSetting()
    }


    private fun openSetting() {
        imageViewSetting.setOnClickListener {
            startActivity(Intent(this, StockMarketArangementActivity::class.java))
        }
    }


    private fun initAdapter() {
        recyclerViewSelectedStock.layoutManager = LinearLayoutManager(this)
        recyclerViewSelectedStock.adapter = SelectedStockAdapter(
            this,
            StockApplicationClass.getStocksList())
    }

    override fun onResume() {
        super.onResume()
        if (StockApplicationClass.getSelectedRowStocksList().isNotEmpty()) {
            Log.d("bugLog", "1")

            textViewItemsNotFound.visibility = View.INVISIBLE

            if (HelperClass.isInternetAvailable(this)) {
                Log.d("bugLog", "2")
                if (!Constants.isFirstRun) {
                    Log.d("bugLog", "2")

                    if (StockApplicationClass.getSelectedRowStocksList().isNotEmpty()) {
                        Log.d("bugLog", "3")

                        textViewItemsNotFound.setText(R.string.no_item_added)
                        textViewItemsNotFound.visibility = View.INVISIBLE
                        updateList(true)
                        /*networkingHelperClass.startJob()*/
                        initAdapter()
                    } else {
                        Log.d("bugLog", "4")

                        textViewItemsNotFound.setText(R.string.no_item_added)
                        textViewItemsNotFound.visibility = View.VISIBLE
                        NetworkOperations().startNetworkRequest()
                    }
                } else {
                    Log.d("bugLog", "5")

                    if (StockApplicationClass.getSelectedRowStocksList().isEmpty()) {
                        Constants.isFirstRun = true
                        updateList(true)
                        textViewItemsNotFound.setText(R.string.no_item)
                        textViewItemsNotFound.visibility = View.VISIBLE
                    }
                }
            }
        } else {
            Log.d("bugLog", "6")

            /*Show No Data found*/
            if (!Constants.isFirstRun) {
                Log.d("bugLog", "7")

                textViewItemsNotFound.setText(R.string.no_item_added)
                textViewItemsNotFound.visibility = View.INVISIBLE
            } else {
                Log.d("bugLog", "8")

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
            updateList(false)

        }
    }


    private fun updateList(shouldActive: Boolean){
        if(shouldActive){
            GlobalScope.launch(Dispatchers.IO){
                NetworkOperations().startNetworkRequest()
                delay(Constants.RESTART_INTERVAL)
                updateList(true)
            }
        }else{
            return
        }

    }
}
