package com.example.capstone.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.capstone.api.MovieApi
import com.example.capstone.api.MovieApiService
import com.example.capstone.model.Actor
import com.example.capstone.model.Movie
import com.example.capstone.model.MovieInfo
import kotlinx.coroutines.withTimeout

class ActorRepository {
    private val actorApiService: MovieApiService = MovieApi.createApi()
    private val _actor : MutableLiveData<List<Actor>> = MutableLiveData()
    val actor: LiveData<List<Actor>> get() = _actor

    suspend fun getActor(actor: String)  {
        try {
            //timeout the request after 5 seconds
            val result = withTimeout(5_000) {
                actorApiService.getActors(actor)
            }

            _actor.value = result.results
        } catch (error: Throwable) {
            throw ActorRefreshError("Unable to refresh actor", error)
        }
    }
    class ActorRefreshError(message: String, cause: Throwable) : Throwable(message, cause)
}