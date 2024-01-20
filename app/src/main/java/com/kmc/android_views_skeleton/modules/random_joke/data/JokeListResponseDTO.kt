package com.kmc.android_views_skeleton.modules.random_joke.data

import com.kmc.android_views_skeleton.modules.random_joke.data.RandomJokeDTO

data class JokeListResponseDTO(
    val total: Int?,
    val result: List<RandomJokeDTO>?
)
