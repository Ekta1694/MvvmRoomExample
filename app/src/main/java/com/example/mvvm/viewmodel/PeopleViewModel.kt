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
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.example.mvvm.MyApplication
import com.example.mvvm.R
import com.example.mvvm.model.People
import com.example.mvvm.retrofit.ApiFactory
import com.example.mvvm.view.database.AppDatabase
import com.example.mvvm.view.database.AppExecutors
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import kotlin.collections.ArrayList

class PeopleViewModel(private var context: Context?) : Observable() {
    var peopleProgress: ObservableInt
    var peopleRecycler: ObservableInt
    var peopleLabel: ObservableInt
    var messageLabel: ObservableField<String>
    var plist: ArrayList<People> = ArrayList()
    private var compositeDisposable: CompositeDisposable? = CompositeDisposable()
   var appDatabase: AppDatabase?


    fun onClickFabLoad(view: View?) {
        initializeViews()
        fetchPeopleList()
    }

    fun setdata() {
        initializeViews()
        fetchPeopleList()
    }

    //It is "public" to show an example of test
    fun initializeViews() {
        peopleLabel.set(View.GONE)
        peopleRecycler.set(View.GONE)
        peopleProgress.set(View.VISIBLE)
    }

    // NOTE: This method can return the rx observer and just subscribe to it in the activity or fragment,
    // an Activity or Fragment needn't to implement from the Observer java class
    // (it was my first approach to avoid RX in UI now we can use LiveData instead)
    private fun fetchPeopleList() {
        val peopleApplication = MyApplication.create(context!!)
        val peopleService = peopleApplication.getPeopleService()
        val disposable = peopleService!!.fetchPeople(ApiFactory.RANDOM_USER_URL)
            ?.subscribeOn(peopleApplication.subscribeScheduler())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe( {
                    it ->     changePeopleDataSet(it!!.peopleList!!)
                peopleProgress.set(View.GONE)
                peopleLabel.set(View.GONE)
                peopleRecycler.set(View.VISIBLE)
            }, { throwable ->  messageLabel.set(context!!.getString(R.string.error_loading_people))
                peopleProgress.set(View.GONE)
                peopleLabel.set(View.VISIBLE)
                peopleRecycler.set(View.GONE)
                throwable.printStackTrace()})
        compositeDisposable!!.add(disposable!!)
    }

    private fun changePeopleDataSet(peoples: List<People>) {
        plist.addAll(peoples)
        AppExecutors.instance!!.diskIO()
            .execute {
                appDatabase!!.peopleDao()!!.delete()
                appDatabase!!.peopleDao()!!.insert(peoples)
                println("insert success")
            }
        setChanged()
        notifyObservers()
    }

//    fun getPeopleList(): List<People> {
//        return plist
//    }

    fun reset() {
        compositeDisposable!!.dispose()
        compositeDisposable = null
        context = null
    }

    init {
        plist = ArrayList()
        peopleProgress = ObservableInt(View.GONE)
        peopleRecycler = ObservableInt(View.GONE)
        peopleLabel = ObservableInt(View.VISIBLE)
        messageLabel =
            ObservableField(context!!.getString(R.string.default_loading_people))
        appDatabase = AppDatabase.getInstance(context!!)
    }
}