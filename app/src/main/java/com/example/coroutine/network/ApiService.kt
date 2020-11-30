package com.example.coroutine.network

import com.example.coroutine.model.MovieEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    suspend fun getListMovie(
        @Query("api_key") api_key:String,
        @Query("language")language:String
    ) : MovieEntity
}