package com.example.capstone.api

import com.example.capstone.model.Movie
import com.example.capstone.model.MovieInfo
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiService {
    @GET("SearchMovie/k_5dvc5nl3/{expression}")
    suspend fun getMovies(@Path("expression") movie: String): Movie

    @GET("Title/k_5dvc5nl3/{id}/Ratings")
    suspend fun getMovieInfo(@Path("id") id: String): MovieInfo
}