package com.kmc.android_views_skeleton.modules.random_joke.ui.jokes_browser

@Suppress("ClassName")
sealed class JokeBrowserViewEvent {

    data class searchTextDidChange(val text: String) : JokeBrowserViewEvent()
}