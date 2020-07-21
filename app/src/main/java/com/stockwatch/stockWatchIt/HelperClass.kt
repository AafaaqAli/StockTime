package com.stockwatch.stockWatchIt

import android.app.job.JobScheduler
import android.content.Context
import android.net.ConnectivityManager
import com.stockwatch.stocks.Constants


class HelperClass {
    companion object{
        fun isJobServiceOn(context: Context): Boolean{
            val scheduler =  context.getSystemService( Context.JOB_SCHEDULER_SERVICE ) as JobScheduler
            var hasBeenScheduled = false
            for(job in scheduler.allPendingJobs){
                if(job.id == Constants.JOB_SERVICE_ID){
                    hasBeenScheduled = true
                    break
                }

            }
            return hasBeenScheduled

        }

        fun isInternetAvailable(context: Context): Boolean{
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
    }
}