package com.jamunoz.devkotlin.moviesapp.network

import com.google.gson.GsonBuilder
import com.jamunoz.devkotlin.moviesapp.core.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val webService: WebService by lazy{
        Retrofit.Builder()
            .baseUrl(Constants.API_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(WebService::class.java)

    }
}