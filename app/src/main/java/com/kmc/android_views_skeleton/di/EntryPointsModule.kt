package com.kmc.android_views_skeleton.di

import com.kmc.android_views_skeleton.modules.random_joke.domain.use_cases.JokeUseCases
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface EntryPointsModule {
    fun jokeUseCases(): JokeUseCases
}