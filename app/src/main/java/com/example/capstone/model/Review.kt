package com.example.capstone.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reviewTable")
data class Review (
    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "rating")
    var rating: Int,

    @ColumnInfo(name = "review")
    var review: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
)