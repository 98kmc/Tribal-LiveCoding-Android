package com.kmc.android_views_skeleton.modules.random_joke

import com.kmc.android_views_skeleton.modules.random_joke.data.JokeListResponseDTO
import com.kmc.android_views_skeleton.modules.random_joke.data.RandomJokeDTO
import com.kmc.android_views_skeleton.modules.random_joke.domain.entity.JokeCategory
import com.kmc.android_views_skeleton.utils.Resource

interface RandomJokeViewModelDelegate {

    fun showJokesBrowser()
}

interface RandomJokeRepositoryRepresentable {

    suspend fun fetchRandomJoke(): Resource<RandomJokeDTO>
    suspend fun fetchRandomJokeWith(category: JokeCategory): Resource<RandomJokeDTO>
    suspend fun searchJoke(text: String) : Resource<JokeListResponseDTO>
}