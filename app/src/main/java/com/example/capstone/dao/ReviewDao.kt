package com.example.capstone.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.capstone.model.Review

@Dao
interface ReviewDao {
    @Query("SELECT * FROM reviewTable")
    fun getAllReviews(): LiveData<List<Review>>

    @Insert
    suspend fun insertReview(review: Review)

    @Query("DELETE FROM reviewTable")
    suspend fun deleteAllReviews()
}