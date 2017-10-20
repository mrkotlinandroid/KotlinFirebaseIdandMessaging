package com.google.firebase.quickstart.fcm
//all codes are taken   from the below source and modified to kotlin langugage

//https://github.com/firebase/quickstart-android


import com.firebase.jobdispatcher.JobParameters
import com.firebase.jobdispatcher.JobService

class MyJobService : JobService() {

    override fun onStartJob(jobParameters: JobParameters): Boolean {
        return false
    }

    override fun onStopJob(jobParameters: JobParameters): Boolean {
        return false
    }

    companion object {

        private val TAG = "MyJobService"
    }

}