package com.example.mvvm.model

import com.google.gson.annotations.SerializedName

class PeopleResponse {
    @SerializedName("results")
    val peopleList: List<People>? = null
}


