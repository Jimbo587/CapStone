package com.example.capstone.repository

import android.content.Context
import com.example.capstone.dao.ReviewDao
import com.example.capstone.database.ReviewDatabase
import com.example.capstone.model.Review

public class ReviewRepository(context: Context) {
    private var reviewDao: ReviewDao

    init {
        val reviewDatabase = ReviewDatabase.getDatabase(context)
        reviewDao = reviewDatabase!!.reminderDao()
    }

    suspend fun getAllReminders(): List<Review> {
        return reviewDao.getAllReviews()
    }

    suspend fun insertReminder(review: Review) {
        reviewDao.insertReview(review)
    }

    suspend fun deleteReminder(review: Review) {
        reviewDao.deleteReview(review)
    }

    suspend fun updateReminder(review: Review) {
        reviewDao.updateReview(review)
    }
}