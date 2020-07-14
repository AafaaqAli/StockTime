package com.example.stocktime

import android.annotation.SuppressLint
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.app.job.JobScheduler.RESULT_SUCCESS
import android.content.ComponentName
import android.content.Context
import android.content.Context.JOB_SCHEDULER_SERVICE
import android.os.Build
import android.util.Log
import com.example.stocks.Constants


class NetworkingHelperClass(context: Context) {
     private var mContext: Context = context

    fun startJob(){
        if(HelperClass.isJobServiceOn(mContext)){
            Log.d("networkingServiceLog", "NetworkingHelperClass: Job service already running")
        }else{
            Log.d("networkingServiceLog", "RequestJobService: Starting Job Service")
            scheduleJob(true)
        }
    }

    fun cancelJob(){
        if(!HelperClass.isJobServiceOn(mContext)){
            scheduleJob(false)
            Log.d("networkingServiceLog", "NetworkingHelperClass: Job service Stoped")

        }else{
            Log.d("networkingServiceLog", "NetworkingHelperClass: Job service already stoped")
        }
    }
    @SuppressLint("ObsoleteSdkInt")
    fun scheduleJob(setEnable: Boolean){
        val jobResult: Int
        val jobInfo: JobInfo
        val jobScheduler: JobScheduler = mContext.getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler

        val componentName = ComponentName(mContext, RequestJobService::class.java )

        if(setEnable){
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N){
                jobInfo = JobInfo.Builder(Constants.JOB_SERVICE_ID, componentName)
                    .setMinimumLatency(Constants.JOB_REQUEST_RESTART_INTERVAL)
                    .setPersisted(true)
                    .build()
            } else {
                jobInfo = JobInfo.Builder(Constants.JOB_SERVICE_ID, componentName)
                    .setPeriodic(5000)
                    .setPersisted(true)
                    .build()
            }

            jobResult =  jobScheduler.schedule(jobInfo)

            if (jobResult == RESULT_SUCCESS) {
                Log.d("networkingServiceLog", "NetworkingHelperClass: Job Schedule started successfully")
            } else {
                Log.d("networkingServiceLog", "NetworkingHelperClass: Job Scheduling Failed")
            }

        }else{
            Log.d("networkingServiceLog", "NetworkingHelperClass: Job Schedule canceled successfully")
            jobScheduler.cancelAll()
        }
    }
}