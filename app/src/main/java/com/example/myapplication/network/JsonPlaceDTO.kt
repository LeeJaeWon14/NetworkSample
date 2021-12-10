package com.example.myapplication.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class JsonPlaceDTO(
    @Expose
    @SerializedName("userId")
    var userId: String,

    @Expose
    @SerializedName("id")
    var id: String,

    @Expose
    @SerializedName("title")
    var title: String,

    @Expose
    @SerializedName("body")
    var body: String
)
