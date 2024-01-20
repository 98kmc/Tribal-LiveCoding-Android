package com.kmc.android_views_skeleton.utils

sealed class Resource<T>(open val data: T? = null, open val error: String? = null) {

    data class Success<T>(override val data: T) : Resource<T>()
    data class Failure<T>(override val error: String) : Resource<T>()
}