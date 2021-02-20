package com.example.mvvm

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.example.mvvm.retrofit.ApiFactory
import com.example.mvvm.retrofit.ApiService
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class MyApplication :MultiDexApplication()
{
    var apiService:ApiService?=null
    var scheduler:Scheduler?=null

//    override fun onCreate() {
//        super.onCreate()
//        MultiDex.install(this);
//
//    }

    companion object
    {
        fun create(context: Context):MyApplication
        {
            return get(context)
        }

        private fun get(context: Context): MyApplication {

            return context.applicationContext as MyApplication
        }

    }

    fun getPeopleService():ApiService?
    {
        if(apiService==null)
        {
            apiService=ApiFactory.create()
        }

        return apiService
    }

    fun subscribeScheduler(): Scheduler?
    {
        if(scheduler==null)
        {
            scheduler= Schedulers.io()
        }
        return scheduler
    }
}