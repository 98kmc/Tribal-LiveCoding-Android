package com.kmc.android_views_skeleton.utils.networking.api

import com.kmc.android_views_skeleton.modules.random_joke.data.JokeListResponseDTO
import com.kmc.android_views_skeleton.modules.random_joke.data.RandomJokeDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ChuckNorrisApi {

    // https://api.chucknorris.io/jokes/random
    @GET("random")
    suspend fun getRandomQuote(): Response<RandomJokeDTO>

    @GET("random")
    suspend fun getRandomQuote(
        @Query("category") category: String
    ): Response<RandomJokeDTO>

    @GET("search")
    suspend fun searchJoke(
        @Query("query") text: String
    ): Response<JokeListResponseDTO>
}