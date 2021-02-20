package com.example.mvvm.retrofit

import com.example.mvvm.model.PeopleResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET
    fun fetchPeople(@Url url: String?): Observable<PeopleResponse?>?

}