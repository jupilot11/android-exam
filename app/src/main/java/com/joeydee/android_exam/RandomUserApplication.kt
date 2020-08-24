package com.joeydee.android_exam

import android.app.Application
import android.content.Context

class RandomUserApplication : Application(){

    companion object{
        lateinit var instance: RandomUserApplication private set
        val context: Context
            get() = instance.applicationContext
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}