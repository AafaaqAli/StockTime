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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : WearableActivity() {
    lateinit var stockDB: StockDB
    lateinit var networkingHelperClass: NetworkingHelperClass
    var selectedRawStocks: List<RawStock> = arrayListOf()

    var isInit: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        stockDB = StockDB.getDatabase(applicationContext)

        if (HelperClass.isInternetAvailable(this)) {
            AndroidNetworking.initialize(applicationContext);
            AndroidNetworking.setParserFactory(JacksonParserFactory())
        }


        networkingHelperClass = NetworkingHelperClass(this)
        Constants.Context = applicationContext
        openSetting()
    }


    private fun openSetting() {
        imageViewSetting.setOnClickListener {
            startActivity(Intent(this, StockMarketArangementActivity::class.java))
        }
    }


    private fun initAdapter() {
        /*set adapter here*/
        recyclerViewSelectedStock.layoutManager = LinearLayoutManager(this)
        recyclerViewSelectedStock.adapter = SelectedStockAdapter(
                this,
                StockApplicationClass.getSelectedStocksList())
        recyclerViewSelectedStock.adapter!!.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        if (StockApplicationClass.getSelectedRowStocksList().isNotEmpty()) {
            textViewItemsNotFound.visibility = View.INVISIBLE

            if (HelperClass.isInternetAvailable(this)) {
                if (!Constants.isFirstRun) {
                    if (StockApplicationClass.getSelectedRowStocksList().isNotEmpty()) {
                        GlobalScope.launch(Dispatchers.IO) {
                            StockApplicationClass.getSelectedRowStocksList().forEach { rawStock ->
                                stockDB.stockDAO().insertAll(rawStock)
                            }
                        }
                    }
                    if (StockApplicationClass.getSelectedStocksList().isNotEmpty()) {
                        textViewItemsNotFound.setText(R.string.no_item_added)
                        textViewItemsNotFound.visibility = View.VISIBLE
                        updateList(true)
                        /*networkingHelperClass.startJob()*/
                        initAdapter()
                    } else {
                        textViewItemsNotFound.setText(R.string.no_item_added)
                        textViewItemsNotFound.visibility = View.VISIBLE
                        GlobalScope.launch(Dispatchers.IO) {
                            selectedRawStocks = stockDB.stockDAO().getAll()
                            NetworkOperations(selectedRawStocks).startNetworkRequest()
                        }
                    }
                } else {
                    if (StockApplicationClass.getSelectedRowStocksList().isEmpty()) {
                        Constants.isFirstRun = true
                        updateList(true)
                        textViewItemsNotFound.setText(R.string.no_item)
                        textViewItemsNotFound.visibility = View.VISIBLE
                    }
                }
            }
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
            updateList(false)

        }
    }


    private fun updateList(shouldActive: Boolean) {
        if (shouldActive) {
            GlobalScope.launch(Dispatchers.IO) {
                NetworkOperations(selectedRawStocks
                ).startNetworkRequest()
                delay(Constants.RESTART_INTERVAL)
                updateList(true)
            }
        } else {
            return
        }

    }
}
