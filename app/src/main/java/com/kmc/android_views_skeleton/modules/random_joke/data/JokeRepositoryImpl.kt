package com.kmc.android_views_skeleton.modules.random_joke.data

import com.kmc.android_views_skeleton.modules.random_joke.RandomJokeRepository
import com.kmc.android_views_skeleton.modules.random_joke.domain.entity.JokeCategory
import com.kmc.android_views_skeleton.utils.networking.SafeApiCaller
import com.kmc.android_views_skeleton.utils.networking.api.ChuckNorrisApi
import javax.inject.Inject

class JokeRepositoryImpl @Inject constructor(
    private val api: ChuckNorrisApi
) : RandomJokeRepository, SafeApiCaller {

    override suspend fun fetchRandomJoke() = safeApiCall {
        api.getRandomQuote()
    }

    override suspend fun fetchRandomJokeWith(category: JokeCategory) = safeApiCall {
        api.getRandomQuote(category.toString().lowercase())
    }

    override suspend fun searchJoke(text: String) = safeApiCall {
        api.searchJoke(text)
    }
}