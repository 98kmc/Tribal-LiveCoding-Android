package com.kmc.android_views_skeleton.modules.random_joke.ui.random_joke

import com.kmc.android_views_skeleton.infrastructure.ViewModel
import com.kmc.android_views_skeleton.modules.random_joke.RandomJokeViewModelDelegate
import com.kmc.android_views_skeleton.modules.random_joke.domain.entity.Joke
import com.kmc.android_views_skeleton.modules.random_joke.domain.entity.JokeCategory
import com.kmc.android_views_skeleton.modules.random_joke.ui.random_joke.RandomJokeViewEvent.*
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
class RandomJokeViewModel private constructor() : ViewModel<RandomJokeViewEvent> {

    private lateinit var useCases: JokeUseCases
    private lateinit var coordinator: RandomJokeViewModelDelegate
    private val scope = CoroutineScope(Dispatchers.IO)
    private val _currentJoke = MutableStateFlow(Joke.emptyJoke())
    private var currentCategory: JokeCategory = JokeCategory.All

    /***
     * Exposed properties
     */
    val currentJoke = _currentJoke.asStateFlow()

    override fun onEvent(event: RandomJokeViewEvent) {
        when (event) {
            viewDidAppear -> getNewJoke()
            didTapTheScreen -> getNewJoke()
            didTapSearchBtn -> coordinator.showJokesBrowser()
            is didSelectCategory -> {
                currentCategory = event.selectedCategory
                getNewJoke()
            }
        }
    }

    /***
     * Private Methods
     */
    private fun getNewJoke() {

        scope.launch {

            val lastJoke = _currentJoke.value

            do {

                val result = useCases.getRandomJoke(selectedCategory = currentCategory)
                if (result is Resource.Success) {

                    withContext(Dispatchers.Main) {
                        _currentJoke.value = result.data
                    }
                }
            } while (lastJoke == currentJoke.value)
        }
    }

    companion object {

        fun new(useCases: JokeUseCases, coordinator: RandomJokeViewModelDelegate) =
            RandomJokeViewModel().apply {
                this.useCases = useCases
                this.coordinator = coordinator
            }

    }
}