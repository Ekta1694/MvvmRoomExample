/**
 * Copyright 2016 Erik Jhordan Rey.
 *
 * Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by
 * applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See
 * the License for the specific language governing permissions and limitations under the License.
 */
package com.example.mvvm.viewmodel

import android.content.Context
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableInt
import com.example.mvvm.MyApplication
import com.example.mvvm.model.People
import com.example.mvvm.retrofit.ApiFactory
import com.example.mvvm.view.database.AppDatabase
import com.example.mvvm.view.database.AppExecutors
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import kotlin.collections.ArrayList


class GalleryViewModel(private var context: Context?) : Observable() {
    var Progressbar: ObservableInt
    var rvData: ObservableInt
    private val strList: ArrayList<String> = ArrayList()
    private var compDisposable: CompositeDisposable? = CompositeDisposable()
    var appDatabase: AppDatabase?
    var people: ArrayList<People> =
        ArrayList()
    var finish = false
    fun setdata() {
        initializeViews()
        //fetchgallerylist();
          fetchlistfromdb()
    }

    //It is "public" to show an example of test
    fun initializeViews() {
        rvData.set(View.GONE)
        Progressbar.set(View.VISIBLE)
    }

    private fun fetchgallerylist() {
        val peopleApplication = MyApplication.create(context!!)
        val peopleService = peopleApplication.getPeopleService()
        val disposable = peopleService!!.fetchPeople(ApiFactory.RANDOM_USER_URL)
            ?.subscribeOn(peopleApplication.subscribeScheduler())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
              {
                    val gson = Gson()
                    val json = gson.toJson(it!!.peopleList)
                    for (i in it!!.peopleList!!.indices) {
                        strList.add(it.peopleList!!.get(i).picture!!.large)
                    }
                    changePeopleDataSet(strList)
                    Progressbar.set(View.GONE)
                    rvData.set(View.VISIBLE)
                },
                {
                        throwable -> throwable.printStackTrace() })
        compDisposable!!.add(disposable!!)
    }

    private fun fetchlistfromdb() {
        AppExecutors.instance!!.diskIO().execute(Runnable {

            var peoplist=appDatabase!!.peopleDao()!!.getPeopleList()
            //people.addAll()
            println("peoplelist size--" + peoplist!!.size)
            for (i in peoplist.indices) {
                strList.add(peoplist.get(i)!!.picture!!.large)
            }
            // System.out.println("people data--"+people.get(0).getPicture());
            (context as AppCompatActivity).runOnUiThread {
                changePeopleDataSet(strList)
                Progressbar.set(View.GONE)
                rvData.set(View.VISIBLE)
            }
        })
    }


    private fun changePeopleDataSet(peoples: List<String>) {
       // strList.addAll(peoples!!);
        setChanged()
        notifyObservers()
    }

    fun getstrList(): List<String> {
        return strList
    }

    fun reset() {
        compDisposable!!.dispose()
        compDisposable = null
        context = null
    }

    init {
        Progressbar = ObservableInt(View.GONE)
        rvData = ObservableInt(View.GONE)
           appDatabase = AppDatabase.getInstance(context!!)
    }
}