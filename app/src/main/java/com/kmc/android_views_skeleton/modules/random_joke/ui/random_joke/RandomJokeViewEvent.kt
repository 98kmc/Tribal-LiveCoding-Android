package com.kmc.android_views_skeleton.modules.random_joke.ui.random_joke

import com.kmc.android_views_skeleton.modules.random_joke.domain.entity.JokeCategory

@Suppress("ClassName")
sealed class RandomJokeViewEvent {

    data object viewDidAppear : RandomJokeViewEvent()
    data object didTapTheScreen : RandomJokeViewEvent()
    data object didTapSearchBtn : RandomJokeViewEvent()
    data class didSelectCategory(val selectedCategory: JokeCategory) : RandomJokeViewEvent()
}
