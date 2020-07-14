package com.example.stocktime

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log

class RequestJobService : JobService() {

    override fun onStopJob(p0: JobParameters?): Boolean {
        jobFinished(p0, true)
        return true
    }

    override fun onStartJob(prams: JobParameters?): Boolean {
        Log.d("networkingServiceLog", "RequestJobService: Job Started onStartJob()")
/*
        NetworkOperations().startNetworkRequest()
*/
        return true
    }
}
