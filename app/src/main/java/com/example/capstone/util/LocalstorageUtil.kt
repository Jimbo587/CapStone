package com.example.capstone.util

import android.content.Context

class LocalstorageUtil {

    companion object{
        const val PREFERENCE_FILE_KEY = "com.example.capstone.util.PREFERENCE_FILE_KEY"
        const val MOVIE_ID_KEY = "PERSON_ID"

        fun fetchMovieId(context: Context): Int? {
            return fetchIntByKey(context, MOVIE_ID_KEY, 0)
        }

        fun fetchIntByKey(context: Context, key: String, defaultValue: Int = 1): Int? {
            return context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE)
                    .getInt(key, defaultValue)
        }
    }
}