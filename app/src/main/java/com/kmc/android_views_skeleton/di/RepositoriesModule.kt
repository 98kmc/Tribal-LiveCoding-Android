package com.kmc.android_views_skeleton.di

import com.kmc.android_views_skeleton.modules.random_joke.RandomJokeRepository
import com.kmc.android_views_skeleton.modules.random_joke.data.JokeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {

    @Binds
    abstract fun bindRandomJokeRepository(impl: JokeRepositoryImpl): RandomJokeRepository
}