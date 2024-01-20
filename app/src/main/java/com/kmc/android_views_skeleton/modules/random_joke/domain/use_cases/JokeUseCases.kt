package com.kmc.android_views_skeleton.modules.random_joke.domain.use_cases

import com.kmc.android_views_skeleton.modules.random_joke.RandomJokeRepositoryRepresentable
import com.kmc.android_views_skeleton.modules.random_joke.data.toJoke
import com.kmc.android_views_skeleton.modules.random_joke.domain.entity.Joke
import com.kmc.android_views_skeleton.modules.random_joke.domain.entity.JokeCategory
import com.kmc.android_views_skeleton.utils.Resource
import com.kmc.android_views_skeleton.utils.Resource.*
import javax.inject.Inject

class JokeUseCases @Inject constructor(
    private val randomJokeRepo: RandomJokeRepositoryRepresentable,
) {

    suspend fun getRandomJoke(selectedCategory: JokeCategory): Resource<Joke> {
        val result = if (selectedCategory == JokeCategory.All) randomJokeRepo.fetchRandomJoke()
        else randomJokeRepo.fetchRandomJokeWith(category = selectedCategory)
        return when (result) {
            is Success -> Success(result.data.toJoke())
            is Failure -> Failure(result.error)
        }
    }

    suspend fun searchJokes(text: String): Resource<List<Joke>> {

        return when (val result = randomJokeRepo.searchJoke(text = text)) {
            is Success -> Success(result.data.result?.map {  it.toJoke() } ?: listOf())
            is Failure -> Failure(result.error)
        }
    }

}