package com.kmc.android_views_skeleton.di

import android.content.Context
import com.kmc.android_views_skeleton.modules.random_joke.domain.use_cases.JokeUseCases
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface HiltEntryPoints {
    fun jokeUseCases(): JokeUseCases
}

fun injectFromHilt(context: Context) = EntryPoints.get(context, HiltEntryPoints::class.java)