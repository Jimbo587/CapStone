package com.example.capstone.model

import com.google.gson.annotations.SerializedName

class Actor(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("image")
    var image: String? = null,
    @SerializedName("description")
    var description: String? = null,
    var results: List<Actor>? = null
) {
}