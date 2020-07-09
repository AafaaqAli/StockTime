package com.example.stocktime

import android.content.Intent
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.view.View
import com.example.stocks.StockApplicationClass

class SortActivity : WearableActivity(), View.OnClickListener {
    lateinit var networkingHelperClass: NetworkingHelperClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sorted)

        networkingHelperClass = NetworkingHelperClass(applicationContext)
    }

    override fun onClick(view: View?) {
        sortRequestedData(view!!.tag.toString())
    }


    private fun sortRequestedData(character: String){
        /*Sort data here*/
        if(StockApplicationClass.getStocksList().isNotEmpty()){

        }else{
            /*startNetworking*/
            networkingHelperClass.startJob()
        }


        /*temp Intent (setData inStockApplication Class)*/
        startActivity(Intent(this, SortedDataActivity::class.java))
    }
}