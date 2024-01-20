package com.kmc.android_views_skeleton.modules.random_joke.data

import com.kmc.android_views_skeleton.modules.random_joke.domain.entity.Joke

@Suppress("PropertyName")
data class RandomJokeDTO(

    val categories: List<String>?,
    val icon_url: String?,
    val id: String?,
    val url: String?,
    val value: String?
)

fun RandomJokeDTO.toJoke() = Joke(
    categories = this.categories ?: listOf(),
    imageUrl = this.icon_url ?: "",
    id = this.id ?: "",
    url = this.url ?: "",
    text = this.value ?: ""
)
/***
 * {
 *     "categories":[],
 *     "created_at":"2020-01-05 13:42:21.179347",
 *     "icon_url":"https://assets.chucknorris.host/img/avatar/chuck-norris.png",
 *     "id":"M_4KNb_EQQOyRR_UNtNdew","updated_at":"2020-01-05 13:42:21.179347",
 *     "url":"https://api.chucknorris.io/jokes/M_4KNb_EQQOyRR_UNtNdew",
 *     "value":"Chuck Norris sits on his tv and watches the couch."
 * }
 */