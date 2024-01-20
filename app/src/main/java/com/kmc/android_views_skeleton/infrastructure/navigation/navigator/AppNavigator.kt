package com.kmc.android_views_skeleton.infrastructure.navigation.navigator

interface AppNavigator {

    fun pushFragment(fragment: FragmentView, animated: Boolean = true)

    fun present(fragment: FragmentView, fullScreen: Boolean = false)

    fun popFragment(animated: Boolean = true)

    fun popFragment(count: Int, animated: Boolean = true)

    fun popTo(fragment: FragmentView, animated: Boolean = true)
}