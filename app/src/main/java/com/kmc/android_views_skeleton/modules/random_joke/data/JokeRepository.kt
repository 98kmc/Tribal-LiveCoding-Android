package com.kmc.android_views_skeleton.modules.random_joke.data

import com.kmc.android_views_skeleton.modules.random_joke.RandomJokeRepositoryRepresentable
import com.kmc.android_views_skeleton.modules.random_joke.domain.entity.JokeCategory
import com.kmc.android_views_skeleton.utils.networking.SafeApiCaller
import com.kmc.android_views_skeleton.utils.networking.api.JokeApi
import javax.inject.Inject

class JokeRepository @Inject constructor(
    private val api: JokeApi
) : RandomJokeRepositoryRepresentable, SafeApiCaller {

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