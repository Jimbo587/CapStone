package com.example.capstone.model

import com.google.gson.annotations.SerializedName

class Movie (
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("image")
    var image: String? = null,
    var results: List<Movie>? = null
){
}