package com.example.mypostapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClicte {
    var retrofit=Retrofit.Builder()       //Retrofit creates the API client
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T>buildAPI(apiInterface: Class<T>):T {
        return retrofit.create(apiInterface)
    }

}