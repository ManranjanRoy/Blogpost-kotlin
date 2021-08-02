package com.dca.androidpractical.network

import com.dca.androidpractical.model.post
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET("app_api/")
    fun getPosts() : Call<List<post>>

    companion object {

        var BASE_URL = "http://panel.qfonapp.xyz/"
        fun create() : ApiInterface {
               val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}