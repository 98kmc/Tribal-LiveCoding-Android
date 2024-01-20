package com.kmc.android_views_skeleton.modules.random_joke.domain.entity

data class Joke(
    val categories: List<String>,
    val imageUrl: String,
    val id: String,
    val url: String,
    val text: String
) {
    companion object {

        fun emptyJoke() = Joke(
            categories = listOf(),
            imageUrl = "",
            id = "",
            url = "",
            text = ""
        )
    }
}