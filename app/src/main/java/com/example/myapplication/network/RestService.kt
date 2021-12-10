package com.example.myapplication.network

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface RestService {
    @GET(Constants.URL)
    fun callGet() : Call<JsonObject>

    @GET(Constants.URL)
    fun callGet(@Body postId: String) : Call<JsonPlaceDTO>

    @GET("${Constants.URL}/{path}")
    fun callGetPath(@Path("path") path: String) : Call<JsonPlaceDTO>
}