package com.kmc.android_views_skeleton.modules.random_joke

import com.kmc.android_views_skeleton.di.EntryPointsModule
import com.kmc.android_views_skeleton.modules.random_joke.domain.use_cases.JokeUseCases
import com.kmc.android_views_skeleton.modules.random_joke.ui.jokes_browser.JokeBrowserFragment
import com.kmc.android_views_skeleton.modules.random_joke.ui.jokes_browser.JokesBrowserViewModel
import com.kmc.android_views_skeleton.modules.random_joke.ui.random_joke.RandomJokeFragment
import com.kmc.android_views_skeleton.modules.random_joke.ui.random_joke.RandomJokeViewModel
import com.kmc.android_views_skeleton.utils.navigation.AppRouter
import com.kmc.android_views_skeleton.utils.navigation.Coordinator
import com.kmc.android_views_skeleton.utils.navigation.navigator.newFragment
import dagger.hilt.EntryPoints
import dagger.hilt.android.scopes.ActivityScoped

@ActivityScoped
class RandomJokeCoordinator<T: AppRouter>(
    private val router: T
) : Coordinator, RandomJokeViewModelDelegate {

    private val useCases: JokeUseCases

    init {
        val appContext = router.context
        val entryPoint = EntryPoints.get(appContext, EntryPointsModule::class.java)
        useCases = entryPoint.jokeUseCases()
    }

    override fun start() {

        val viewModel = RandomJokeViewModel.new(useCases = useCases, coordinator = this)
        val fragment = newFragment(name = "RandomJoke") {
            RandomJokeFragment.newInstance(viewModel)
        }

        router.navigator.pushFragment(fragment, animated = false)
    }

    override fun showJokesBrowser() {
        val vm = JokesBrowserViewModel.new(useCases = useCases)
        val fragment = newFragment(name = "SearchJokes") { JokeBrowserFragment.newInstance(viewModel = vm) }
        router.navigator.present(fragment)
    }
}