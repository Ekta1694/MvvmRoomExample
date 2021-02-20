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

import android.view.View
import com.example.mvvm.model.People

class PeopleDetailViewModel(private val people: People) {
    val fullUserName: String
        get() = people.name!!.title + "." + people.name!!.first + " " + people.name!!.last

    val userName: String
        get() = people.login!!.username

    val email: String
        get() = people.email!!

    val emailVisibility: Int
        get() = if (people.hasEmail()) View.VISIBLE else View.GONE

    val cell: String
        get() = people.cell!!

    val pictureProfile: String
        get() = people.picture!!.large

    val address: String
        get() = (people.location!!.street.name
                + " "
                + people.location!!.street.number + " "
                + people.location!!.city
                + " "
                + people.location!!.state)

    val gender: String
        get() = people.gender!!

}