package com.example.capstone.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reviewTable")
data class Review (
    @ColumnInfo(name = "reminder")
    var name: String,

    @ColumnInfo(name = "reminder")
    var rating: Int,

    @ColumnInfo(name = "reminder")
    var review: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
)