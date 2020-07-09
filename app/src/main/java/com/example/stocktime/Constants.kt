package com.example.stocks

import android.content.Context

class Constants {
    companion object{
        lateinit var Context: Context
        const val JOB_SERVICE_ID = 110
        const val JOB_SERVICE_NAME = "networking_job_service"
        const val JOB_REQUEST_INTERVALS: Long = (1000 * 1 * 60) * 5//1.5 minutes (1000 x 60 x 1.5)
        const val JOB_REQUEST_RESTART_INTERVALS: Long = 1000 * 60 * 15 //15 minutes
        var isFirstRun: Boolean = false
    }
}