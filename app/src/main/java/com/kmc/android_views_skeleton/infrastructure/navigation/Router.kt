package com.kmc.android_views_skeleton.infrastructure.navigation

import android.content.Context
import com.kmc.android_views_skeleton.AppTransition
import com.kmc.android_views_skeleton.infrastructure.navigation.navigator.AppNavigator
import com.kmc.android_views_skeleton.infrastructure.navigation.navigator.Navigator

interface Router<Route> {

    fun exit()

    fun process(route: Route)
}

interface AppRouter: Router<AppTransition> {

    val context: Context
    val navigator: AppNavigator
}
