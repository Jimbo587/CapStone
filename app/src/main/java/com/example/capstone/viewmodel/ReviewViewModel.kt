package com.example.capstone.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.capstone.model.Review
import com.example.capstone.repository.ReviewRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReviewViewModel(application: Application): AndroidViewModel(application) {

    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val reviewRepository = ReviewRepository(application.applicationContext)

    val review: LiveData<List<Review>> = reviewRepository.getAllReviews()

    fun  insertReview(review: Review) {
        ioScope.launch {
            reviewRepository.insertReview(review)
        }
    }

    fun deleteAllReviews(){
        ioScope.launch {
            withContext(Dispatchers.IO) {
                reviewRepository.deleteAllReviews()
            }
        }
    }
}