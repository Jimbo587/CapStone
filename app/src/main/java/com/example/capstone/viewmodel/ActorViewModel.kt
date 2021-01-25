package com.example.capstone.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstone.model.Actor
import com.example.capstone.repository.ActorRepository
import com.example.capstone.repository.MovieRepository
import kotlinx.coroutines.launch

class ActorViewModel(): ViewModel() {
    private val actorRepository = ActorRepository()

    val actor = actorRepository.actor
    var currentActor: MutableLiveData<Actor> = MutableLiveData()

    private val _errorText: MutableLiveData<String> = MutableLiveData()

    fun setCurrentActor(actor: Actor){
        currentActor.value = actor
    }

    fun getActors(actor: String) {
        viewModelScope.launch {
            try {
                //the movieRepository sets it's own livedata property
                //our own movie LiveData property points to te one in that repository
                actorRepository.getActor(actor)
            } catch (error: MovieRepository.MovieRefreshError) {
                _errorText.value = error.message
                Log.e("Actor error", error.cause.toString())
            }
        }
    }
}