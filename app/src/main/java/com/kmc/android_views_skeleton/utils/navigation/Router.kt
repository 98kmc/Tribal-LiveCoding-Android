package com.kmc.android_views_skeleton.utils.navigation

import android.content.Context
import com.kmc.android_views_skeleton.AppTransition
import com.kmc.android_views_skeleton.utils.navigation.navigator.AppNavigator
import com.kmc.android_views_skeleton.utils.navigation.navigator.Navigator

interface Router<Route> {

    fun exit()

    fun process(route: Route)
}

interface AppRouter: Router<AppTransition> {

    val context: Context
    val navigator: AppNavigator
}
