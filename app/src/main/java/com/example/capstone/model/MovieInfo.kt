package com.example.capstone.model

import com.google.gson.annotations.SerializedName

class MovieInfo (
        @SerializedName("id")
        var id: String? = null,
        @SerializedName("title")
        var title: String? = null,
        @SerializedName("originalTitle")
        var originalTitle: String? = null,
        @SerializedName("fullTitle")
        var fullTitle: String? = null,
        @SerializedName("image")
        var image: String? = null,
        @SerializedName("releaseDate")
        var releaseDate: String? = null,
        @SerializedName("runtimeStr")
        var runTimeStr: String? = null,
        @SerializedName("awards")
        var awards: String? = null,
        @SerializedName("stars")
        var stars: String? = null,
        @SerializedName("plot")
        var plot: String? = null
) {
}