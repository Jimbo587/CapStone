package com.example.capstone.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.capstone.model.Movie
import com.example.capstone.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel(): ViewModel() {

    private val movieRepository = MovieRepository()
    val movie = movieRepository.movie
    var currentMovie: MutableLiveData<Movie> = MutableLiveData()
    private val _errorText: MutableLiveData<String> = MutableLiveData()

    fun getCurrentMovie(): LiveData<Movie>{
        return currentMovie
    }

    fun setCurrentMovie(movie: Movie){
        currentMovie.value = movie
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