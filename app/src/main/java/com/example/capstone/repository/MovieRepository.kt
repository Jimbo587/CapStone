package com.example.capstone.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.capstone.api.MovieApi
import com.example.capstone.api.MovieApiService
import com.example.capstone.model.Movie
import kotlinx.coroutines.withTimeout

class MovieRepository {
    private val movieApiService: MovieApiService = MovieApi.createApi()
    private val _movie : MutableLiveData<List<Movie>> = MutableLiveData()
    val movie: LiveData<List<Movie>> get() = _movie

//    private val _movieInfo : MutableLiveData<List<MovieInfo>> = MutableLiveData()
//    val movieInfo: LiveData<List<MovieInfo>> get() = _movieInfo

    suspend fun getMovies(movie: String)  {
        try {
            //timeout the request after 5 seconds
            val result = withTimeout(5_000) {
                movieApiService.getMovies(movie)
            }

            _movie.value = result.results
        } catch (error: Throwable) {
            throw MovieRefreshError("Unable to refresh movie", error)
        }
    }

    suspend fun getMovieInfo(id: String)  {
        try {
            //timeout the request after 5 seconds
            val result = withTimeout(5_000) {
                movieApiService.getMovieInfo(id)
            }

            _movie.value = result.results
        } catch (error: Throwable) {
            throw MovieRefreshError("Unable to refresh movie", error)
        }
    }

    class MovieRefreshError(message: String, cause: Throwable) : Throwable(message, cause)
}