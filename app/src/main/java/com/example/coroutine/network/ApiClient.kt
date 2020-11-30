package com.example.coroutine.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private var retrofit : Retrofit? = null

    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/w500"
    const val API_KEY = "e9906e4a57e2cf19f54dcba5a135d47f"
    const val LANGUAGE = "en-US"

    private fun getClient(): Retrofit? {

        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client= OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        if (retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
        return this.retrofit
    }

    fun instance(): ApiService = getClient()!!.create(ApiService::class.java)
}