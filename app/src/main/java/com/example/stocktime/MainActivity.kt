package com.example.stocktime

import android.content.Intent
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.view.View
import com.androidnetworking.AndroidNetworking
import com.example.stocks.Constants
import com.example.stocks.StockApplicationClass
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: WearableActivity(){
    // TODO: 7/9/20 create a separate class for networking
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidNetworking.initialize(applicationContext);
        initNetworking()


        if(!Constants.isFirstRun){
            Constants.isFirstRun = true
            if(StockApplicationClass.getSeletedStocksList().isEmpty()){
                textViewItemsNotFound.setText(R.string.no_item_added)
            }
        }else{
            if(StockApplicationClass.getSeletedStocksList().isEmpty()){
                textViewItemsNotFound.setText(R.string.no_item)
            }
        }

        openSetting()

    }



    private fun openSetting(){
        imageViewSetting.setOnClickListener{
            startActivity(Intent(this, SortActivity::class.java))
        }
    }

    private fun startJob(){
       /* if(!job.isActive()){
            ***Start the job***
        }*/
    }

    private fun cancelJob(){
       /* if(job.isActive){
            *end job here*
        }*/
    }


    private fun initNetworking(){
        /*start networking here*/
    }

    private fun initAdapter(){
        /*set adapter here*/
    }

    override fun onResume() {
        super.onResume()
        if(StockApplicationClass.getSeletedStocksList().isNotEmpty()){
            textViewItemsNotFound.visibility = View.INVISIBLE
            /*InitAdapter here*/

        }else{
            /*Show No Data found*/
            if(Constants.isFirstRun){
                textViewItemsNotFound.visibility = View.VISIBLE
                textViewItemsNotFound.setText(R.string.no_item)
            }else{
                textViewItemsNotFound.visibility = View.INVISIBLE
                textViewItemsNotFound.setText(R.string.no_item_added)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if(StockApplicationClass.getStocksList().isEmpty()){
            initNetworking()
        }else{
            /*show data*/
            initAdapter()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

       /* if(job.isActive){
            ***end the job***
        }*/
    }
}
