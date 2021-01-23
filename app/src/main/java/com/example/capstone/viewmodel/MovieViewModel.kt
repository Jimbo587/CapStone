package com.example.capstone.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.capstone.model.Movie
import com.example.capstone.model.MovieInfo
import com.example.capstone.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel(): ViewModel() {

    private val movieRepository = MovieRepository()
    val movie = movieRepository.movie
//    val movieInfo = movieRepository.movieInfo
    var currentMovie: MutableLiveData<Movie> = MutableLiveData()
//    var currentMovieInfo: MutableLiveData<MovieInfo> = MutableLiveData()
    private val _errorText: MutableLiveData<String> = MutableLiveData()

    fun getCurrentMovie(): LiveData<Movie>{
        return currentMovie
    }

    fun setCurrentMovie(movie: Movie){
        currentMovie.value = movie
    }

//    fun getCurrentMovieInfo(): LiveData<MovieInfo>{
//        return currentMovieInfo
//    }
//
//    fun setCurrentMovieInfo(movieInfo: MovieInfo){
//        currentMovieInfo.value = movieInfo
//    }

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