package com.example.capstone.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.capstone.dao.ReviewDao
import com.example.capstone.database.ReviewDatabase
import com.example.capstone.model.Review

class ReviewRepository(context: Context) {
    private var reviewDao: ReviewDao

    init {
        val reviewDatabase = ReviewDatabase.getDatabase(context)
        reviewDao = reviewDatabase!!.reminderDao()
    }

    fun getAllReviews(): LiveData<List<Review>> {
        return reviewDao.getAllReviews()
    }

    suspend fun insertReview(review: Review) {
        reviewDao.insertReview(review)
    }

    suspend fun deleteReview(review: Review) {
        reviewDao.deleteReview(review)
    }
}