package com.example.capstone.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstone.model.Movie
import com.example.capstone.model.MovieInfo
import com.example.capstone.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel(): ViewModel() {

    private val movieRepository = MovieRepository()

    val movie = movieRepository.movie
    var currentMovie: MutableLiveData<Movie> = MutableLiveData()

    val movieInfo = movieRepository.movieInfo
    var currentMovieInfo: MutableLiveData<MovieInfo> = MutableLiveData()

    private val _errorText: MutableLiveData<String> = MutableLiveData()

    fun getCurrentMovie(): LiveData<Movie>{
        return currentMovie
    }

    fun setCurrentMovie(movie: Movie){
        currentMovie.value = movie
    }

    fun getCurrentMovieInfo(): LiveData<MovieInfo>{
        return movieInfo
    }

    fun setCurrentMovieInfo(movieInfo: MovieInfo){
        currentMovieInfo.value = movieInfo
    }

    fun getMovieId (id : String){
        viewModelScope.launch {
            try {
                //the movieRepository sets it's own livedata property
                //our own movie LiveData property points to te one in that repository
                movieRepository.getMovieInfo(id)
            } catch (error: MovieRepository.MovieRefreshError) {
                _errorText.value = error.message
                Log.e("Movie error", error.cause.toString())
            }
        }
    }

    fun getMovies(movie: String) {
        viewModelScope.launch {
            try {
                //the movieRepository sets it's own livedata property
                //our own movie LiveData property points to te one in that repository
                movieRepository.getMovies(movie)
            } catch (error: MovieRepository.MovieRefreshError) {
                _errorText.value = error.message
                Log.e("Movie error", error.cause.toString())
            }
        }
    }
}