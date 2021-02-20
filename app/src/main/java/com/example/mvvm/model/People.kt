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
package com.example.mvvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity
class People : Serializable {
    //  @SerializedName("gender")
    var gender: String? = null

    //  @SerializedName("name")
    var name: Name? = null

    // @SerializedName("location")
    var location: Location? = null

    //  @SerializedName("email")
    var email: String? = null

    //  @SerializedName("login")
    var login: Login? = null

    //   @SerializedName("cell")
    var cell: String? = null

    //  @SerializedName("picture")
    var picture: Picture? = null

    //  @SerializedName("id")
    var id: Id? = null

    @PrimaryKey(autoGenerate = true)
    var uid: Long = 0
    var fullName: String? = null
        get() = name!!.title + "." + name!!.first + " " + name!!.last

    fun gUid(): Long {
        uid = Random().nextLong()
        return uid
    }

    fun sUid(uid: Long) {
        this.uid = uid
    }

    fun hasEmail(): Boolean {
        return email != null && !email!!.isEmpty()
    }
}