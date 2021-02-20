/**
 * Copyright 2016 Erik Jhordan Rey.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.mvvm.viewmodel

import android.content.Context
import android.view.View
import androidx.databinding.BaseObservable
import com.example.mvvm.model.People
import com.example.mvvm.view.activity.PeopleDetailActivity

class ItemPeopleViewModel(
    private val context: Context,
    private var people: People
) : BaseObservable() {
    val fullName: String
        get() = people.name!!.title + "." + people.name!!.first + " " + people.name!!.last

    val cell: String
        get() = people.cell!!

    val email: String
        get() = people.email!!

    val pictureProfile: String
        get() = people.picture!!.medium

    fun onItemClick(view: View) {
        context.startActivity(PeopleDetailActivity.launchDetail(view.context, people))
    }

    fun setPeopleList(people: People) {
        this.people = people
        notifyChange()
    }

}