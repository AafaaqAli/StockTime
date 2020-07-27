package com.stockwatch.stockWatchIt

import android.app.job.JobParameters
import android.app.job.JobService

class RequestJobService : JobService() {

    override fun onStopJob(p0: JobParameters?): Boolean {
        jobFinished(p0, true)
        return true
    }

    override fun onStartJob(prams: JobParameters?): Boolean {
        return true
    }
}
