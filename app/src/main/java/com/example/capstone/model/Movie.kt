package com.example.capstone.model

import com.google.gson.annotations.SerializedName

class Movie (
    var title: String? = null,
    @SerializedName("backdrop_path")
    var backDropPath: String? = null,
    @SerializedName("poster_path")
    var posterPath: String? = null,
    @SerializedName("release_date")
    var releaseDate: String? = null,
    @SerializedName("vote_average")
    var rating: String? = null,
    var overview: String? = null
){
}