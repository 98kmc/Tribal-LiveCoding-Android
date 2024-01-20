package com.kmc.android_views_skeleton

import android.app.Application
import android.content.Context
import com.kmc.android_views_skeleton.AppTransition.showRandomJoke
import com.kmc.android_views_skeleton.infrastructure.navigation.AppRouter
import com.kmc.android_views_skeleton.infrastructure.navigation.Coordinator
import com.kmc.android_views_skeleton.infrastructure.navigation.navigator.Navigator
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application(), AppRouter, Coordinator {
    override val context: Context
        get() = this

    override lateinit var navigator: Navigator
    private val firstRoute: AppTransition  = showRandomJoke
    override fun start() {

        process(firstRoute)
    }

    override fun exit() {
    }

    override fun process(route: AppTransition) {
        val coordinator = route.coordinatorFor(this)
        coordinator.start()
    }
}