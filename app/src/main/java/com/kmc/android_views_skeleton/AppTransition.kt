package com.kmc.android_views_skeleton

import com.kmc.android_views_skeleton.modules.random_joke.RandomJokeCoordinator
import com.kmc.android_views_skeleton.infrastructure.navigation.AppRouter
import com.kmc.android_views_skeleton.infrastructure.navigation.Coordinator

@Suppress("ClassName")
sealed class AppTransition {

    data object showRandomJoke : AppTransition()

    fun <R: AppRouter>coordinatorFor(router: R): Coordinator
        = when (this) {
        showRandomJoke -> RandomJokeCoordinator(router = router)
    }
}