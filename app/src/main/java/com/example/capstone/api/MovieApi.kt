package com.example.capstone.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieApi {

    companion object {
        /**
         * @return [TriviaApiService] The service class off the retrofit client.
         */

        private val baseUrl = "https://imdb-internet-movie-database-unofficial.p.rapidapi.com/"
        private val apiKey= "4f81bfe985mshb5ac52b0e11dc86p19ce51jsn772bc5134e2d"

        class MovieInterceptor: Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val url = chain.request().url.newBuilder().addQueryParameter("api_key",
                        apiKey).build()
                val newRequest = chain.request().newBuilder().url(url).build()
                return chain.proceed(newRequest)
            }
        }

        fun createApi(): MovieApiService {
            // Create an OkHttpClient to be able to make a log of the network traffic
            val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addInterceptor(MovieInterceptor())
                    .build()

            // Create the Retrofit instance
            val triviaApi = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            // Return the Retrofit TriviaApiService
            return triviaApi.create(MovieApiService::class.java)
        }
    }

}