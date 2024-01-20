package com.kmc.android_views_skeleton.modules.random_joke.ui.jokes_browser

import android.os.Parcelable
import com.kmc.android_views_skeleton.modules.random_joke.domain.entity.Joke
import com.kmc.android_views_skeleton.modules.random_joke.domain.use_cases.JokeUseCases
import com.kmc.android_views_skeleton.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.parcelize.Parcelize

@Suppress("PARCELABLE_PRIMARY_CONSTRUCTOR_IS_EMPTY", "PROPERTY_WONT_BE_SERIALIZED")
@Parcelize
class JokesBrowserViewModel private constructor() : Parcelable {

    private val scope = CoroutineScope(Dispatchers.IO)
    private lateinit var useCases: JokeUseCases
    private var _jokesList = MutableStateFlow<List<Joke>>(listOf())

    val jokesList get() = _jokesList.asStateFlow()

    fun onEvent(event: JokeBrowserViewEvent) {

        when (event) {
            is JokeBrowserViewEvent.searchTextDidChange -> searchJokes(event.text)
        }
    }

    private fun searchJokes(text: String) {

        scope.launch {

            val result = useCases.searchJokes(text)

            if (result is Resource.Success) {

                withContext(Dispatchers.Main) {
                    _jokesList.value = result.data
                }
            }
        }
    }

    companion object {

        fun new(useCases: JokeUseCases) =
            JokesBrowserViewModel().apply {
                this.useCases = useCases
            }
    }
}