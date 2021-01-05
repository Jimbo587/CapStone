package com.example.capstone.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.capstone.dao.ReviewDao
import com.example.capstone.model.Review

@Database(entities = [Review::class], version = 1, exportSchema = false)
abstract class ReviewDatabase : RoomDatabase() {

    abstract fun reminderDao(): ReviewDao

    companion object {
        private const val DATABASE_NAME = "REMINDER_DATABASE"

        @Volatile
        private var reminderRoomDatabaseInstance: ReviewDatabase? = null

        fun getDatabase(context: Context): ReviewDatabase? {
            if (reminderRoomDatabaseInstance == null) {
                synchronized(ReviewDatabase::class.java) {
                    if (reminderRoomDatabaseInstance == null) {
                        reminderRoomDatabaseInstance = Room.databaseBuilder(
                            context.applicationContext,
                            ReviewDatabase::class.java, DATABASE_NAME
                        )
                            .build()
                    }
                }
            }
            return reminderRoomDatabaseInstance
        }
    }

}