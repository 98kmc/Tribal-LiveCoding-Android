package com.kmc.android_views_skeleton.modules.random_joke

import com.kmc.android_views_skeleton.di.injectFromHilt
import com.kmc.android_views_skeleton.modules.random_joke.ui.jokes_browser.JokeBrowserFragment
import com.kmc.android_views_skeleton.modules.random_joke.ui.jokes_browser.JokesBrowserViewModel
import com.kmc.android_views_skeleton.modules.random_joke.ui.random_joke.RandomJokeFragment
import com.kmc.android_views_skeleton.modules.random_joke.ui.random_joke.RandomJokeViewModel
import com.kmc.android_views_skeleton.infrastructure.navigation.AppRouter
import com.kmc.android_views_skeleton.infrastructure.navigation.Coordinator
import com.kmc.android_views_skeleton.infrastructure.navigation.navigator.newFragmentView
import dagger.hilt.android.scopes.ActivityScoped

@ActivityScoped
class RandomJokeCoordinator<T: AppRouter>(
    private val router: T
) : Coordinator, RandomJokeViewModelDelegate {

    private val useCases = injectFromHilt(router.context).jokeUseCases()

    override fun start() {

        val viewModel = RandomJokeViewModel.new(useCases = useCases, coordinator = this)
        val fragment = newFragmentView(name = "RandomJoke") {
            RandomJokeFragment.newInstance(viewModel)
        }

        router.navigator.pushFragment(fragment, animated = false)
    }

    override fun showJokesBrowser() {
        val vm = JokesBrowserViewModel.new(useCases = useCases)
        val fragment = newFragmentView(name = "SearchJokes") { JokeBrowserFragment.newInstance(viewModel = vm) }
        router.navigator.present(fragment)
    }
}